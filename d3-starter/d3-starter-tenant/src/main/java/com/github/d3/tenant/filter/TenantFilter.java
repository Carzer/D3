package com.github.d3.tenant.filter;

import com.github.d3.tenant.TenantContext;
import com.github.d3.tenant.TenantInfo;
import com.github.d3.tenant.code.TenantCode;
import com.github.d3.tenant.constants.TenantConstants;
import com.github.d3.tenant.exception.TenantException;
import com.github.d3.util.jackson.JsonUtil;
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
@WebFilter(urlPatterns = "/**", filterName = "TenantFilter", dispatcherTypes = DispatcherType.REQUEST)
@Slf4j
public final class TenantFilter extends OncePerRequestFilter {

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
        // 设置租户信息
        setTenantInfo(request.getHeader(TenantConstants.HEADER_TENANT_INFO));
        // 其他操作
        filterChain.doFilter(request, response);
        // 移除租户信息
        removeTenantInfo();
    }

    /**
     * 设置租户信息
     *
     * @param tenantInfo 租户信息
     */
    private void setTenantInfo(String tenantInfo) {
        if (StringUtils.hasText(tenantInfo)) {
            TenantInfo info = JsonUtil.fromJson(tenantInfo, TenantInfo.class);
            TenantContext.TenantInfoHolder.setInfo(info);
            if (log.isDebugEnabled()) {
                log.debug("got tenant info to set, current thread [{}] set tenant info, id [{}]", Thread.currentThread().getName(), info.getTenantId());
            }
        } else {
            throw new TenantException(TenantCode.TENANT_INFO_NOT_FOUND);
        }
    }

    /**
     * 清除租户信息
     */
    private void removeTenantInfo() {
        TenantContext.TenantInfoHolder.removeInfo();
    }
}
