package com.github.d3.filter;

import com.github.d3.Context;
import com.github.d3.constants.CommonConstants;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 统一过滤器
 *
 * @author Carzer1020@163.com
 * @since 2020-11-11
 */
@Component
@WebFilter(urlPatterns = "/**", filterName = "CommonFilter", dispatcherTypes = DispatcherType.REQUEST)
@Slf4j
public final class CommonFilter extends OncePerRequestFilter {

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
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 设置来源系统信息
        setSourceInfo(request.getHeader(CommonConstants.HEADER_SOURCE));
        // 其他操作
        filterChain.doFilter(request, response);
        // 移除来源系统信息
        removeSourceInfo();
    }

    /**
     * 设置来源系统信息
     *
     * @param source 来源系统编码
     */
    private void setSourceInfo(String source) {
        if (StringUtils.hasText(source)) {
            Context.SourceHolder.setSource(source);
            if (log.isDebugEnabled()) {
                log.debug("got source to set, current thread [{}] set source [{}] ", Thread.currentThread().getName(), source);
            }
        }
    }

    /**
     * 移除来源系统信息
     */
    private void removeSourceInfo() {
        Context.SourceHolder.removeSource();
    }
}
