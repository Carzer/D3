package com.github.d3.filter.trim;

import com.github.d3.constants.CommonConstants;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 参数过滤Filter
 *
 * @author Carzer1020@163.com
 * @since 2020-10-26
 */
@Component
@WebFilter(urlPatterns = "/**", filterName = "TrimFilter", dispatcherTypes = DispatcherType.REQUEST)
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class TrimFilter extends OncePerRequestFilter {

    /**
     * 过滤方法
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain 过滤链
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws IOException, ServletException {
        boolean isDebugEnabled = log.isDebugEnabled();
        if (isDebugEnabled) {
            log.debug("进入Trim Filter，开始判断是否需要进行过滤");
        }
        if (noTrim(request)) {
            if (isDebugEnabled) {
                log.debug("Header[{}]，值为true，不需要过滤", CommonConstants.NO_TRIM_HEADER);
            }
            filterChain.doFilter(request, response);
        } else {
            if (isDebugEnabled) {
                log.debug("Header[{}]，值非true，开始过滤", CommonConstants.NO_TRIM_HEADER);
            }
            RequestTrimWrapper paramsRequest = new RequestTrimWrapper(request);
            filterChain.doFilter(paramsRequest, response);
        }
    }

    /**
     * 不过滤空格
     *
     * @param request request
     * @return 是否不过滤空格
     */
    private boolean noTrim(HttpServletRequest request) {
        String headerValue = request.getHeader(CommonConstants.NO_TRIM_HEADER);
        return Boolean.TRUE.equals(Boolean.valueOf(headerValue));
    }

}