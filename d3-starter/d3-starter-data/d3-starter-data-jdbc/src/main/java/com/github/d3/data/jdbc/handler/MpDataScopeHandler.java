package com.github.d3.data.jdbc.handler;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.d3.data.jdbc.property.MpDataControlProperties;
import com.github.d3.handler.DataScopeHandler;
import com.github.d3.util.DurationUtil;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据权限信息handler
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MpDataScopeHandler implements DataScopeHandler {

    /**
     * 组织缓存
     */
    private static final Cache<Long, List<Expression>> ORG_IDS_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(DurationUtil.halfHour()).maximumSize(100).build();

    /**
     * 自定义属性
     */
    private final MpDataControlProperties properties;

    /**
     * 根据表名判断是否忽略拼接组织条件
     * <p>
     * 默认都要进行解析并拼接组织条件
     *
     * @param tableName 表名
     * @return 是否忽略, true:表示忽略，false:需要解析并拼接组织条件
     */
    public boolean ignoreTable(String tableName) {
        return properties.getIgnoreTables().stream().anyMatch(e -> e.equalsIgnoreCase(tableName));
    }

    /**
     * 获取数据范围
     *
     * @return 数据范围
     */
    public Expression getDataScope() {
        return null;
    }

}
