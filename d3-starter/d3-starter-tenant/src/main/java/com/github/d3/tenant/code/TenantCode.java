package com.github.d3.tenant.code;

import com.github.d3.code.RCode;
import lombok.Getter;

/**
 * 租户相关编码（30_000-39_999）
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum TenantCode implements RCode {

    /**
     * 租户信息编码
     */
    TENANT_INFO_NOT_FOUND(30_000L, "租户信息不存在"),
    USER_TENANT_EXISTED(30_001L, "用户已经加入租户!"),
    TENANT_INFO_ERROR(30_002L, "租户信息异常或未加入租户!");

    /**
     * 编码
     */
    private final Long code;

    /**
     * 信息
     */
    private final String message;

    /**
     * 构造方法
     *
     * @param code    编码
     * @param message 信息
     */
    TenantCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }
}
