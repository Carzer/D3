package com.github.d3.exception;

import com.github.d3.code.RCode;

/**
 * 自定义异常
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public abstract class AbstractD3Exception extends RuntimeException {

    /**
     * 消息构造方法
     *
     * @param message 消息构造方法
     */
    protected AbstractD3Exception(String message) {
        super(message);
    }

    /**
     * 默认构造方法
     */
    protected AbstractD3Exception() {

    }

    /**
     * 获取异常编码
     *
     * @return 异常编码
     */
    public abstract RCode getCode();
}
