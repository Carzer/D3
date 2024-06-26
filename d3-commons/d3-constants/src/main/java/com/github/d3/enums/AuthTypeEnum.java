package com.github.d3.enums;

import lombok.Getter;

/**
 * 权限过滤类型enum
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum AuthTypeEnum {

    /**
     * 状态值
     */
    NONE("none", "没有权限"),
    ALL("all", "所有权限"),
    GET("get", "查询权限"),
    ADD("add", "新增权限"),
    PUT("put", "更新权限"),
    DELETE("delete", "删除权限");

    /**
     * 值
     */
    private final String val;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param val  val
     * @param desc desc
     */
    AuthTypeEnum(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
