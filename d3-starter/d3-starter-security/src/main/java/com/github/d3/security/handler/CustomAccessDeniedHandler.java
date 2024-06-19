package com.github.d3.security.handler;

import com.github.d3.R;
import com.github.d3.code.CommonCode;
import com.github.d3.util.jackson.PrintWriterUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 权限拒绝handler
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 权限不足
     *
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param e                   e
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        log.warn(e.getMessage());
        PrintWriterUtil.write(httpServletResponse, new R<String>(CommonCode.FORBIDDEN), HttpServletResponse.SC_FORBIDDEN);
    }
}