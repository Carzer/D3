package com.github.d3.data.jdbc;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源持有者，负责利用ThreadLocal存取数据源名称
 *
 * @author Carzer1020@163.com
 * @see AbstractRoutingDataSource
 * @since 2019-12-13
 */
@UtilityClass
@Slf4j
public class JdbcDynamicDataSourceHolder {

    /**
     * 本地线程共享对象
     */
    private final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * put方法
     *
     * @param name 数据源逻辑名称
     */
    public void putDataSource(String name) {
        THREAD_LOCAL.set(name);
        if (log.isDebugEnabled()) {
            log.debug("Thread: {}, add dataSourceKey:[{}] to thread-local success", Thread.currentThread().getName(), name);
        }
    }

    /**
     * 获取数据源
     * {@link JdbcDynamicDataSource#determineCurrentLookupKey}
     *
     * @return 数据源逻辑名称
     */
    public String getDataSource() {
        return THREAD_LOCAL.get();
    }

    /**
     * 清除数据源名称
     */
    public void removeDataSource() {
        THREAD_LOCAL.remove();
    }
}