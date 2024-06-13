package com.github.d3.exception;

import com.github.d3.code.RCode;
import lombok.Getter;

/**
 * 业务异常类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public final class BizException extends AbstractD3Exception {

    /**
     * 业务码
     */
    private final RCode code;

    /**
     * 构造方法
     *
     * @param code 业务码
     */
    public BizException(RCode code) {
        this.code = code;
    }
}
