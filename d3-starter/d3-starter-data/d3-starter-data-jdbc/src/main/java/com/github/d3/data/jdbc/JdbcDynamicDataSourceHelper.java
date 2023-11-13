package com.github.d3.data.jdbc;

import com.github.d3.util.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源辅助类
 *
 * @author Carzer1020@163.com
 * @since 2021-10-08
 */
@Slf4j
public class JdbcDynamicDataSourceHelper {

    /**
     * 动态数据源
     */
    private JdbcDynamicDataSource dataSource;

    /**
     * 移除非初始化的数据源
     */
    public void removeAdditionalDataSources(String... keys) {
        getDataSource().removeAdditionalDataSources(keys);
    }

    /**
     * 重置数据源为默认环境
     */
    public void clearAdditionalDataSources() {
        getDataSource().clearAdditionalDataSources();
    }

    /**
     * 获取追加数据源
     *
     * @return 追加数据源
     */
    public Map<Object, DataSource> getAdditionalDataSources() {
        return getDataSource().getAdditionalDataSources();
    }

    /**
     * 增加数据源
     *
     * @param map 数据源map
     */
    public void setAdditionalDataSources(Map<String, JdbcDataSource> map) {
        getDataSource().setAdditionalDataSources(map);
    }

    /**
     * 设置数据源
     */
    private JdbcDynamicDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = SpringBeanUtil.getBean(JdbcDynamicDataSource.class);
        }
        return dataSource;
    }

}
