package com.github.d3.exception;

import com.github.d3.code.ErrorCode;
import lombok.Getter;

/**
 * 业务异常类(自定义错误信息)
 *
 * @author Carzer1020@163.com
 * @since 2020-12-21
 */
public final class MsgException extends AbstractException {

    /**
     * 业务码
     */
    @Getter
    private final ErrorCode code;

    /**
     * 构造方法
     *
     * @param message 错误信息
     */
    public MsgException(String message) {
        super(message);
        ErrorCode errorCode = ErrorCode.FAILED;
        errorCode.setMessage(message);
        this.code = errorCode;
    }
}
