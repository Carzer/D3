package com.github.d3.aspect.datasource;

import com.github.d3.enums.DatasourceTypeEnum;

/**
 * 动态数据源切换Provider
 *
 * @author Carzer1020@163.com
 * @since 2023-02-03
 */
public interface DynamicDataSourceProvider {

    /**
     * 方法执行前
     *
     * @param dataSourceName 数据源名称
     */
    void before(String dataSourceName);

    /**
     * 方法执行后
     *
     * @param dataSourceName 数据源名称
     */
    void after(String dataSourceName);

    /**
     * 是否支持当前数据源类型
     *
     * @param datasourceType 数据源类型
     * @return 是否支持当前数据源类型
     */
    boolean supports(DatasourceTypeEnum datasourceType);
}
