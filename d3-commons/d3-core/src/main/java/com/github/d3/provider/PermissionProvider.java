package com.github.d3.provider;

import java.util.Set;

/**
 * 权限信息Provider
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public interface PermissionProvider {

    /**
     * 判断角色是否有当前资源权限
     *
     * @param resource 请求资源
     * @param user     用户名
     * @param roleSet  用户角色
     * @return 是否有权限
     */
    boolean hasAuth(String resource, String user, Set<String> roleSet);
}
