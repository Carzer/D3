package com.github.d3.code;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用编码(自定义错误信息)
 *
 * @author Carzer1020@163.com
 * @since 2020-12-21
 */
public enum ErrorCode implements DmsCode {

    /**
     * 操作码
     */
    FAILED(1001, "操作失败");

    /**
     * 编码
     */
    @Getter
    private final long code;

    /**
     * 信息
     */
    @Getter
    @Setter
    private String message;

    /**
     * 构造方法
     *
     * @param code    编码
     * @param message 信息
     */
    ErrorCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}