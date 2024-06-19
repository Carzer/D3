package com.github.d3.interceptor;

import com.github.d3.Context;
import com.github.d3.constants.CommonConstants;
import com.github.d3.provider.FeignProvider;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Base64;
import java.util.Map;

/**
 * feign调用时会新建client，导致发送请求时无法转发之前的信息
 * 发送请求前，需要主动设置所需信息
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public final class FeignOauth2RequestInterceptor implements RequestInterceptor {

    /**
     * Feign Provider Map
     */
    private final Map<String, FeignProvider> providers;

    /**
     * Feign请求发送时执行的方法
     *
     * @param requestTemplate 请求方法
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            // 设置权限信息
            setAuthentication(requestTemplate);
            // 设置代理信息
            setUserAgent(requestTemplate);
            // 设置请求来源
            setSource(requestTemplate);
            // 处理请求信息
            handleRequest(requestTemplate);
        } catch (Exception e) {
            log.error("FeignOauth2RequestInterceptor exception:", e);
        }
    }

    /**
     * 设置权限信息
     *
     * @param requestTemplate 请求方法
     */
    private void setAuthentication(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // oauth2认证
//            if (authentication instanceof OAuth2Authentication) {
//                OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
//                requestTemplate.header(TokenConstants.AUTHORIZATION, String.format("%s %s", TokenConstants.BEARER_TYPE, auth2AuthenticationDetails.getTokenValue()));
//            }
//            // form登陆认证
//            else
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                // details中的sessionID非Spring security统一管理的，需使用RequestContextHolder
                String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
                if (StringUtils.hasText(sessionId)) {
                    requestTemplate.header("Cookie", String.format("SESSION=%s", Base64.getEncoder().encodeToString(sessionId.getBytes())));
                }
            }
            // 匿名访问
            else if (authentication instanceof AnonymousAuthenticationToken) {
                if (log.isDebugEnabled()) {
                    log.debug("非授权访问，无需处理");
                }
            }
            // 其他的，后续遇到了再补充
            else {
                log.warn("我再想想办法，授权类型为：[{}]", authentication.getClass());
            }
        }
    }

    /**
     * 设置代理信息
     *
     * @param requestTemplate 请求方法
     */
    private void setUserAgent(RequestTemplate requestTemplate) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
            String userAgent = request.getHeader(CommonConstants.USER_AGENT);
            if (StringUtils.hasText(userAgent)) {
                requestTemplate.header(CommonConstants.USER_AGENT, userAgent);
            }
        }
    }

    /**
     * 设置请求来源
     *
     * @param requestTemplate 请求方法
     */
    private void setSource(RequestTemplate requestTemplate) {
        String source = Context.SourceHolder.getSource();
        if (StringUtils.hasText(source)) {
            requestTemplate.header(CommonConstants.HEADER_SOURCE, source);
        }
    }

    /**
     * 处理请求信息
     *
     * @param requestTemplate 请求模板
     */
    private void handleRequest(RequestTemplate requestTemplate) {
        providers.values().forEach(provider -> provider.apply(requestTemplate));
    }
}
