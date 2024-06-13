package com.github.d3.data.jdbc.provider;


import com.github.d3.aspect.datasource.DynamicDataSourceProvider;
import com.github.d3.data.jdbc.JdbcDynamicDataSourceHolder;
import com.github.d3.enums.DatasourceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Jdbc动态数据源切换Provider
 *
 * @author Carzer1020@163.com
 * @since 2023-02-03
 */
@Component
@Slf4j
public class JdbcDynamicDataSourceProvider implements DynamicDataSourceProvider {

    /**
     * 方法执行前
     *
     * @param dataSourceName 数据源名称
     */
    public void before(String dataSourceName) {
        JdbcDynamicDataSourceHolder.putDataSource(dataSourceName);
    }

    /**
     * 方法执行后
     *
     * @param dataSourceName 数据源名称
     */
    public void after(String dataSourceName) {
        JdbcDynamicDataSourceHolder.removeDataSource();
    }

    /**
     * 是否支持当前数据源类型
     *
     * @param datasourceType 数据源类型
     * @return 是否支持当前数据源类型
     */
    @Override
    public boolean supports(DatasourceTypeEnum datasourceType) {
        return DatasourceTypeEnum.JDBC.equals(datasourceType);
    }
}
