package com.github.d3.exception;

import com.github.d3.code.RCode;
import lombok.Getter;

/**
 * D3系统相关异常
 *
 * @author Carzer1020@163.com
 * @since 2023-11-14
 */
@Getter
public final class D3Exception extends AbstractD3Exception {

    /**
     * 异常编码
     */
    private final RCode code;

    /**
     * 构造方法
     *
     * @param code 异常编码
     */
    public D3Exception(RCode code) {
        this.code = code;
    }

}
