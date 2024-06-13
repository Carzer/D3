package com.github.d3.base.handler;


import com.github.d3.R;
import com.github.d3.base.AResult;
import com.github.d3.util.NetUtil;
import com.github.d3.util.jackson.PrintWriterUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * LoginSuccessHandler
 *
 * @author Carzer1020@163.com
 * @since 2023-12-14
 */
@Slf4j
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * 登录成功后执行
     *
     * @param httpServletRequest  request
     * @param httpServletResponse response
     * @param authentication      authentication
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication)
            throws IOException {

        // 输出登录提示信息
        if (log.isDebugEnabled()) {
            log.debug("用户:[{}]登录,IP:[{}]", authentication.getName(), NetUtil.getRemoteIpAddress(httpServletRequest));
        }
        AResult aResult = new AResult();
        aResult.setPrincipal(authentication.getPrincipal());
        aResult.setAuthorities(authentication.getAuthorities());
        R<AResult> result = new R<>(aResult);
        PrintWriterUtil.write(httpServletResponse, result);
    }
}
