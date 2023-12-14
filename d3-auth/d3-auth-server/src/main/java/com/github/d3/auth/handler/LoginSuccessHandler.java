package com.github.d3.auth.handler;


import com.github.d3.R;
import com.github.d3.util.MapUtil;
import com.github.d3.util.jackson.PrintWriterUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
            log.debug("用户:[{}]登录", authentication.getName());
            log.debug("IP:[{}]", httpServletRequest.getRequestURI());
        }
        Map<String, Object> resultMap = new HashMap<>(MapUtil.HASHMAP_DEFAULT_INITIAL_CAPACITY);
        resultMap.put("authorities", authentication.getAuthorities());
        resultMap.put("user", authentication.getPrincipal());
        R<Object> result = new R<>(resultMap);
        PrintWriterUtil.write(httpServletResponse, result);
    }
}
