package com.github.d3.data.jdbc;

import cn.hutool.core.util.ArrayUtil;
import com.github.d3.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

/**
 * 动态数据源实现类
 *
 * @author Carzer1020@163.com
 * @since 2019-12-13
 */
@Slf4j
@SuppressWarnings("all")
public class JdbcDynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 追加的数据源map
     * <p>
     * todo 变更为caffeine缓存，使用RemovalListener处理过期后对数据源的操作
     */
    private final Map<Object, DataSource> additionalDataSources = MapUtil.largerHashMap();

    /**
     * 判断需要使用的数据源
     * <p>
     * 优先使用动态追加的数据源（追加数据源的key与默认配置数据源的key一致时，默认配置数据源等同于被覆盖）
     *
     * @return 需要使用的数据源
     */
    @Override
    protected DataSource determineTargetDataSource() {
        Object lookupKey = determineCurrentLookupKey();
        DataSource dataSource = this.additionalDataSources.get(lookupKey);
        if (lookupKey == null || dataSource == null) {
            return super.determineTargetDataSource();
        }
        return dataSource;
    }

    /**
     * 数据源路由，此方用于产生要选取的数据源逻辑名称
     *
     * @return 数据源逻辑名称
     * @see JdbcDynamicDataSource#determineTargetDataSource()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        // 从共享线程中获取数据源名称
        return resolveSpecifiedLookupKey(JdbcDynamicDataSourceHolder.getDataSource());
    }

    /**
     * 根据传入的key，移除非初始化的数据源
     */
    void removeAdditionalDataSources(String... keys) {
        if (ArrayUtil.isNotEmpty(keys)) {
            for (String key : keys) {
                Object lookupKey = resolveSpecifiedLookupKey(key);
                removeIfExist(lookupKey);
            }
        }
    }

    /**
     * 重置数据源为默认环境
     */
    void clearAdditionalDataSources() {
        if (!CollectionUtils.isEmpty(additionalDataSources)) {
            additionalDataSources.keySet().forEach(this::closeDataSourceByKey);
            additionalDataSources.clear();
        }
    }

    /**
     * 获取追加数据源
     *
     * @return 追加数据源
     */
    Map<Object, DataSource> getAdditionalDataSources() {
        return additionalDataSources;
    }

    /**
     * 增加数据源
     *
     * @param map 数据源map
     */
    void setAdditionalDataSources(Map<String, JdbcDataSource> map) {
        if (!CollectionUtils.isEmpty(map)) {
            map.forEach((key, value) -> {
                Object lookupKey = resolveSpecifiedLookupKey(key);
                DataSource dataSource = resolveSpecifiedDataSource(value);
                removeIfExist(lookupKey);
                additionalDataSources.put(lookupKey, dataSource);
            });
        }
    }

    /**
     * 如果数据源已存在，则执行关闭方法，并从追加数据源中移除
     *
     * @param key 数据源key
     */
    private void removeIfExist(Object key) {
        if (additionalDataSources.containsKey(key)) {
            closeDataSourceByKey(key);
            additionalDataSources.remove(key);
        }
    }

    /**
     * 关闭数据源方法
     *
     * @param key 数据源key
     */
    private void closeDataSourceByKey(Object key) {
        Optional.ofNullable(additionalDataSources.get(key))
                .ifPresent(ds -> {
                    if (ds instanceof JdbcDataSource dataSource) {
                        dataSource.close();
                    }
                });
    }
}