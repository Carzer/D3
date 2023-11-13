package com.github.d3.security.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限工具类
 *
 * @author Carzer1020@163.com
 * @since 2019-09-03
 */
@UtilityClass
public class AuthUtil {

    /**
     * 由security上下文环境中获取当前用户名
     *
     * @return 当前用户名
     */
    public String getName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? "anonymous" : authentication.getName();
    }

    /**
     * 由security上下文环境中获取角色列表
     *
     * @return 当前用户角色列表
     */
    public Set<String> getRolesFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().parallelStream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}