package com.github.d3.data.jdbc.aop;


import cn.hutool.core.annotation.AliasFor;
import com.github.d3.annotations.datasource.Ds;
import jdk.jfr.Experimental;

import java.lang.annotation.*;

/**
 * 目标数据源注解，注解在方法上指定数据源的名称
 *
 * @author Carzer1020@163.com
 * @since 2024-06-11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
//@Ds(type = DatasourceTypeEnum.JDBC)
@Experimental
public @interface JdbcDs {

    /**
     * 数据源的名称
     */
    @AliasFor(annotation = Ds.class)
    String value();
}