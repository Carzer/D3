package com.github.d3.exception;

import com.github.d3.code.MutableCode;
import lombok.Getter;

/**
 * 业务异常类(自定义错误信息)
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public final class MsgException extends AbstractD3Exception {

    /**
     * 业务码
     */
    private final MutableCode code;

    /**
     * 构造方法
     *
     * @param message 错误信息
     */
    public MsgException(String message) {
        super(message);
        this.code = new MutableCode(message);
    }
}
