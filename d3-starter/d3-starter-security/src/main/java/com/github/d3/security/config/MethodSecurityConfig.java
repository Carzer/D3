package com.github.d3.security.config;

import com.github.d3.provider.PermissionProvider;
import com.github.d3.security.permission.DefaultPermissionProvider;
import com.github.d3.security.permission.MethodSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 注解配置权限相关配置
 *
 * @author Carzer1020@163.com
 * @since 2020-11-19
 */
@Order
@EnableMethodSecurity(securedEnabled = true)
//@Configuration
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    /**
     * 权限信息Provider
     */
    private PermissionProvider permissionProvider;

    /**
     * 设置权限信息Provider
     *
     * @param permissionProvider 权限信息Provider
     */
    @Autowired
    public void setPermissionProvider(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
    }

    /**
     * 创建默认表达式
     *
     * @return org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
     */
    @Override
    protected org.springframework.security.access.expression.method.MethodSecurityExpressionHandler createExpressionHandler() {
        MethodSecurityExpressionHandler expressionHandler = new MethodSecurityExpressionHandler();
        expressionHandler.setPermissionProvider(permissionProvider);
        return expressionHandler;
    }

    /**
     * 自定义权限判断
     */
    @Bean
    @ConditionalOnMissingBean(PermissionProvider.class)
    public PermissionProvider permissionProvider() {
        return new DefaultPermissionProvider();
    }

}
