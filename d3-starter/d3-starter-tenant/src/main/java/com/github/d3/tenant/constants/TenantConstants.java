package com.github.d3.tenant.constants;

import lombok.experimental.UtilityClass;

/**
 * 租户常量
 *
 * @author Carzer1020@163.com
 * @since 2023-02-17
 */
@UtilityClass
public class TenantConstants {

    /**
     * request header中，tenant info 字段
     */
    public final String HEADER_TENANT_INFO = "tenant_info";

    /**
     * 系统租户字段
     */
    public final String SYSTEM_TENANT_ID_COLUMN = "tenant_id";
}
