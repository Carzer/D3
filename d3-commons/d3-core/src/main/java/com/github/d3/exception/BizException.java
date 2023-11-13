package com.github.d3.exception;

import com.github.d3.code.DmsCode;
import lombok.Getter;

/**
 * 系统异常类
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
public final class BizException extends AbstractException {

    /**
     * 业务码
     */
    @Getter
    private final DmsCode code;

    /**
     * 构造方法
     *
     * @param code 业务码
     */
    public BizException(DmsCode code) {
        this.code = code;
    }
}
