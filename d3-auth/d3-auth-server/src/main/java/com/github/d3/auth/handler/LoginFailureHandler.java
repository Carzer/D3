package com.github.d3.auth.handler;

import com.github.d3.R;
import com.github.d3.auth.code.AuthCode;
import com.github.d3.exception.BizException;
import com.github.d3.util.jackson.PrintWriterUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录失败handler
 *
 * @author Carzer1020@163.com
 * @since 2023-12-14
 */
@Slf4j
@Component
@SuppressWarnings("unused")
public class LoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * 授权失败
     *
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param e                   e
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException {
        R<String> result;
        Throwable throwable = e.getCause();
        result = switch (throwable) {
            case LockedException lockedException -> new R<>(AuthCode.USER_LOCKED);
            case AccountExpiredException accountExpiredException -> new R<>(AuthCode.USER_EXPIRED);
            case BizException bizException -> new R<>(bizException.getCode());
            case null, default -> new R<>(AuthCode.BAD_CREDENTIALS);
        };
        PrintWriterUtil.write(httpServletResponse, result);
    }
}
