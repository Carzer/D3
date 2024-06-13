package com.github.d3.tenant;

import com.github.d3.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 租户信息传输类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Setter
@Getter
public final class TenantInfo implements BaseDTO {

    @Serial
    private static final long serialVersionUID = -1376562031590759211L;

    /**
     * 区域信息
     * 预留字段
     */
    private String area;

    /**
     * 数据源
     */
    private String datasource;

    /**
     * schema
     */
    private String schema;

    /**
     * 租户ID
     */
    private Long tenantId;

    @Override
    public Serializable getId() {
        return tenantId;
    }
}
