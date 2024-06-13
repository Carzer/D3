package com.github.d3.security.permission;

import com.github.d3.provider.PermissionProvider;
import lombok.Setter;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

/**
 * 表达式处理Handler
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public class MethodSecurityExpressionHandler
        extends DefaultMethodSecurityExpressionHandler {

    /**
     * trustResolver
     */
    private final AuthenticationTrustResolver trustResolver =
            new AuthenticationTrustResolverImpl();

    /**
     * 权限信息Provider
     */
    @Setter
    private PermissionProvider permissionProvider;

    /**
     * 创建表达式操作
     *
     * @param authentication 权限
     * @param invocation     反射对象
     * @return 表达式操作
     */
    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {
        MethodSecurityExpressionRoot root =
                new MethodSecurityExpressionRoot(authentication);
        root.setThis(invocation.getThis());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        root.setDefaultRolePrefix(getDefaultRolePrefix());
        // 设置鉴权相关功能使用自定义模块
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setPermissionProvider(this.permissionProvider);
        return root;
    }

}