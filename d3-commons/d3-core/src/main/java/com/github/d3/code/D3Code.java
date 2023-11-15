package com.github.d3.code;

import java.io.Serializable;

/**
 * 通用编码
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
public interface D3Code extends Serializable {

    /**
     * 操作编码
     *
     * @return 操作编码
     */
    long getCode();

    /**
     * 操作描述
     *
     * @return 操作描述
     */
    String getMessage();
}