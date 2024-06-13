package com.github.d3.data.jdbc.config;

import com.github.d3.data.jdbc.JdbcDataSource;
import com.github.d3.data.jdbc.JdbcDynamicDataSource;
import com.github.d3.data.jdbc.JdbcDynamicDataSourceHelper;
import com.github.d3.data.jdbc.property.JdbcDataSourceProperties;
import com.github.d3.exception.MsgException;
import com.github.d3.util.MapUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 数据源配置
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcDataSourceAutoConfiguration {

    /**
     * 配置项
     */
    private final JdbcDataSourceProperties properties;

    /**
     * 动态多数据源
     *
     * @return 数据源
     * @see JdbcDynamicDataSourceHelper 运行时动态变更数据源
     */
    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        Map<String, JdbcDataSource> jdbc = properties.getJdbc();
        if (CollectionUtils.isEmpty(jdbc)) {
            throw new MsgException("no data sources configuration available, at least one is required");
        }
        // 按照目标数据源名称和目标数据源对象的映射存放在Map中
        Map<Object, Object> targetDataSources = MapUtil.hashMap(jdbc.size());
        targetDataSources.putAll(jdbc);
        // 采用AbstractRoutingDataSource的对象包装多数据源(DynamicDataSource的父类)
        JdbcDynamicDataSource dataSource = new JdbcDynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        // 查找map中的第一个数据源作为默认数据源，如果找不到，就抛出异常
        JdbcDataSource defaultDs = jdbc.entrySet().stream().findFirst().orElseThrow().getValue();
        dataSource.setDefaultTargetDataSource(defaultDs);
        return dataSource;
    }

    /**
     * 事务管理器
     *
     * @param dataSource 数据源
     * @return 事务管理器
     */
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
