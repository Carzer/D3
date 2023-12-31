package com.github.d3.exception;

import com.github.d3.code.D3Code;
import lombok.Getter;

/**
 * 业务异常类
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
@Getter
public final class BizException extends AbstractD3Exception {

    /**
     * 业务码
     */
    private final D3Code code;

    /**
     * 构造方法
     *
     * @param code 业务码
     */
    public BizException(D3Code code) {
        this.code = code;
    }
}
