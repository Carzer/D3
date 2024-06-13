package com.github.d3.data.redis.provider;

import com.github.d3.aspect.datasource.DynamicDataSourceProvider;
import com.github.d3.enums.DatasourceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * redis数据源切换
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Component
@Slf4j
public class RedisDynamicDataSourceProvider implements DynamicDataSourceProvider {

    /**
     * 方法执行前
     *
     * @param dataSourceName 数据源名称
     */
    @Override
    public void before(String dataSourceName) {
        RedisDynamicDataSourceHolder.set(dataSourceName);
    }

    /**
     * 方法执行后
     *
     * @param dataSourceName 数据源名称
     */
    @Override
    public void after(String dataSourceName) {
        RedisDynamicDataSourceHolder.remove();
    }

    /**
     * 是否支持当前数据源类型
     *
     * @param datasourceType 数据源类型
     * @return 是否支持当前数据源类型
     */
    @Override
    public boolean supports(DatasourceTypeEnum datasourceType) {
        return DatasourceTypeEnum.REDIS.equals(datasourceType);
    }
}
