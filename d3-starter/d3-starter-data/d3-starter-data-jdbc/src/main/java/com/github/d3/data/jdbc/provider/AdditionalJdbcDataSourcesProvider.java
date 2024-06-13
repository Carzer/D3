package com.github.d3.data.jdbc.provider;

import com.github.d3.data.jdbc.JdbcDataSource;

import java.util.List;

/**
 * 追加数据源provider
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public interface AdditionalJdbcDataSourcesProvider {

    /**
     * 获取所有自定义的追加数据源
     *
     * @return 所有自定义的追加数据源
     */
    List<JdbcDataSource> getAll();
}
