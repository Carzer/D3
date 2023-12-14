package com.github.d3.auth.handler;

import com.github.d3.R;
import com.github.d3.util.jackson.PrintWriterUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * LogoutSuccessHandler
 *
 * @author Carzer1020@163.com
 * @since 2023-12-14
 */
@Slf4j
@Component
public class D3LogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * 登出成功
     *
     * @param request        request
     * @param response       response
     * @param authentication authentication
     * @throws IOException IOException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 输出登出提示信息
        PrintWriterUtil.write(response, R.success());
    }
}
