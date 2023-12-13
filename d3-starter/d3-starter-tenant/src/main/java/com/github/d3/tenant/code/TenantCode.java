package com.github.d3.tenant.code;

import com.github.d3.code.D3Code;
import lombok.Getter;

/**
 * 租户相关编码（10_000-19_999）
 *
 * @author Carzer1020@163.com
 * @since 2023-12-12
 */
@Getter
public enum TenantCode implements D3Code {

    /**
     * 租户信息编码
     */
    TENANT_INFO_NOT_FOUND(10_000L, "租户信息不存在");

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
