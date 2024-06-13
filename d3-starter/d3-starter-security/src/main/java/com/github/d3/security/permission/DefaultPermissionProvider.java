package com.github.d3.security.permission;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.d3.provider.PermissionProvider;
import com.github.d3.util.DurationUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Set;

/**
 * 权限信息Provider
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Slf4j
public class DefaultPermissionProvider implements PermissionProvider {

    /**
     * 权限信息缓存
     */
    private static final Cache<String, Set<String>> PERMISSION_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(DurationUtil.tenMinutes())
            .maximumSize(100).build();

    /**
     * 判断角色是否有当前资源权限
     *
     * @param resource 资源
     * @param roleSet  角色
     * @return 是否有权限
     */
    @Override
    public boolean hasAuth(String resource, String username, Set<String> roleSet) {
        return true;
    }

    /**
     * 根据角色获取相关权限信息
     *
     * @param osCode   系统编码
     * @param roleCode 角色编码
     * @return 权限信息
     */
    private Set<String> getResourceDefineValue(String osCode, String roleCode) {
        return Collections.emptySet();
    }

}
