package com.github.d3.data.jdbc.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.github.d3.data.jdbc.property.MpTenantControlProperties;
import com.github.d3.tenant.constants.TenantConstants;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * 租户handler
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Component
@ConditionalOnClass(TenantConstants.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MpTenantHandler implements TenantLineHandler {

    /**
     * 自定义属性
     */
    private final MpTenantControlProperties properties;

    /**
     * 获取租户ID字段
     *
     * @return 租户ID字段
     */
    @Override
    public String getTenantIdColumn() {
        return TenantConstants.SYSTEM_TENANT_ID_COLUMN;
    }

    /**
     * 判断表名是否被忽略
     * 忽略语句
     *
     * @param tableName 表名
     * @return 结果
     */
    @Override
    public boolean ignoreTable(String tableName) {
        return properties.getIgnoreTables().stream().anyMatch(e -> e.equalsIgnoreCase(tableName));
    }

    /**
     * 获取租户ID
     *
     * @return 租户ID
     */
    @Override
    public Expression getTenantId() {
        return null;
    }
}
