package com.github.d3.entity;

import java.io.Serializable;

/**
 * 基础类
 *
 * @author Carzer1020@163.com
 * @since 2019-08-22
 */
public interface BaseEntity extends Serializable {

    /**
     * 获取ID
     *
     * @return ID
     */
    Serializable getId();
}
