package com.github.d3.annotations.log;


import java.lang.annotation.*;

/**
 * 定义注解
 *
 * @author Carzer1020@163.com
 * @since 2019-12-23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface OpLog {

    /**
     * 操作说明
     */
    String value() default "";

    /**
     * 描述
     */
    String description() default "";
}