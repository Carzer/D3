package com.github.d3.tenant.exception;

import com.github.d3.code.D3Code;
import com.github.d3.exception.AbstractD3Exception;
import lombok.Getter;

/**
 * 租户相关异常
 *
 * @author Carzer1020@163.com
 * @since 2023-12-12
 */
@Getter
public class TenantException extends AbstractD3Exception {

    /**
     * 异常编码
     */
    private final D3Code code;

    /**
     * 构造方法
     *
     * @param code 异常编码
     */
    public TenantException(D3Code code) {
        this.code = code;
    }
}
