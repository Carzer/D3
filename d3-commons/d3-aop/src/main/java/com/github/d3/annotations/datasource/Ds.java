package com.github.d3.annotations.datasource;


import com.github.d3.enums.DatasourceTypeEnum;

import java.lang.annotation.*;

/**
 * 目标数据源注解，注解在方法上指定数据源的名称
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Ds {

    /**
     * 数据源的名称
     */
    String value();

    /**
     * 数据源类型
     */
    DatasourceTypeEnum type() default DatasourceTypeEnum.JDBC;
}