package com.github.d3.security.annotations;


import com.github.d3.enums.AuthTypeEnum;

import java.lang.annotation.*;

/**
 * 替换自定义权限表达式中的{}
 *
 * @author Carzer1020@163.com
 * @since 2020-10-15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface PreAuth {

    /**
     * 替换自定义权限表达式中的{}
     */
    String value() default "";

    /**
     * 过滤类型
     * {@link AuthTypeEnum}
     */
    AuthTypeEnum exclude() default AuthTypeEnum.NONE;
}
