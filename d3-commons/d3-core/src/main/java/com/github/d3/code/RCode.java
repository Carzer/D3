package com.github.d3.code;

import java.io.Serializable;

/**
 * 返回码
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
public interface RCode extends Serializable {

    /**
     * 操作编码
     *
     * @return 操作编码
     */
    Long getCode();

    /**
     * 操作描述
     *
     * @return 操作描述
     */
    String getMessage();
}