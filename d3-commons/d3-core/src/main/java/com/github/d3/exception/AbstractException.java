package com.github.d3.exception;

import com.github.d3.code.DmsCode;

/**
 * 自定义异常
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
public abstract class AbstractException extends RuntimeException {

    /**
     * 消息构造方法
     *
     * @param message 消息构造方法
     */
    protected AbstractException(String message) {
        super(message);
    }

    /**
     * 默认构造方法
     */
    protected AbstractException() {

    }

    /**
     * 获取异常编码
     *
     * @return 异常编码
     */
    public abstract DmsCode getCode();
}
