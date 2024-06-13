package com.github.d3.enums;

import lombok.Getter;

/**
 * 性别枚举类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum GenderEnum {

    /**
     * 性别
     */
    UNKNOWN(-1, "unknown", "不便透露"),
    MALE(0, "male", "男"),
    FEMALE(1, "female", "女");

    /**
     * 数据库中的值
     */
    private final int val;

    /**
     * key
     */
    private final String key;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param val  val
     * @param key  key
     * @param desc desc
     */
    GenderEnum(int val, String key, String desc) {
        this.val = val;
        this.key = key;
        this.desc = desc;
    }
}
