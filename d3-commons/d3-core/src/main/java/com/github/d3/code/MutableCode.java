package com.github.d3.code;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用编码(自定义错误信息)
 *
 * @author Carzer1020@163.com
 * @since 2020-12-21
 */

@Getter
public class MutableCode implements D3Code {

    /**
     * 编码,默认（1001, "操作失败"）
     */
    private final long code;

    /**
     * 信息
     */
    @Setter
    private String message;

    /**
     * 构造方法
     *
     * @param code    编码
     * @param message 信息
     */
    public MutableCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造方法
     *
     * @param message 信息
     */
    public MutableCode(String message) {
        this(1_001, message);
    }

}