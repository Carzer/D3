package com.github.d3.security.permission;

import cn.hutool.core.annotation.AnnotationUtil;
import com.github.d3.constants.PunctuationConstants;
import com.github.d3.enums.AuthTypeEnum;
import com.github.d3.provider.PermissionProvider;
import com.github.d3.security.annotations.PreAuth;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 表达式处理
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Slf4j
public class MethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    /**
     * 缓存
     */
    private static final Map<String, String> REPLACE_CACHE = new ConcurrentHashMap<>();

    /**
     * filterObject
     */
    @Getter
    @Setter
    private Object filterObject;

    /**
     * returnObject
     */
    @Getter
    @Setter
    private Object returnObject;

    /**
     * target
     */
    private Object target;

    /**
     * 权限信息Provider
     */
    @Setter
    private PermissionProvider permissionProvider;

    /**
     * 构造方法
     *
     * @param authentication authentication
     */
    public MethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    /**
     * 自定义表达式
     *
     * @param resource 资源
     * @return 是否可以访问
     */
    public boolean hasAuth(String resource) {
        try {
            if (resource.contains(PunctuationConstants.REPLACE)) {
                Class<?> clazz = this.target.getClass();
                String replace = REPLACE_CACHE.get(clazz.getName());
                if (!StringUtils.hasText(replace) && AnnotationUtil.hasAnnotation(clazz, PreAuth.class)) {
                    AuthTypeEnum authTypeEnum = AnnotationUtil.getAnnotationValue(clazz, PreAuth.class, "exclude");
                    if (AuthTypeEnum.ALL.equals(authTypeEnum) || resource.endsWith(authTypeEnum.getVal())) {
                        return true;
                    }
                    replace = AnnotationUtil.getAnnotationValue(clazz, PreAuth.class);
                    REPLACE_CACHE.put(clazz.getName(), replace);
                }
                resource = resource.replace(PunctuationConstants.REPLACE, replace);
            }
            // 获取用户名及所有角色
            String username = this.getAuthentication().getName();
            Set<String> roleSet = this.getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
            boolean result = this.permissionProvider.hasAuth(resource, username, roleSet);
            if (log.isDebugEnabled()) {
                log.debug("用户[{}]判断资源[{}]的权限，角色列表[{}]，执行结果[{}]", username, resource, roleSet, result);
            }
            return result;
        } catch (Exception e) {
            log.error("权限认证出错:", e);
            return false;
        }
    }

    /**
     * 获取target
     */
    @Override
    public Object getThis() {
        return target;
    }

    /**
     * 设置target
     *
     * @param target target
     */
    void setThis(Object target) {
        this.target = target;
    }

}