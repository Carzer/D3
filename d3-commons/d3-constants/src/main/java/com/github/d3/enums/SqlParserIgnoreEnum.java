package com.github.d3.enums;

import lombok.Getter;

/**
 * 租户过滤枚举
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum SqlParserIgnoreEnum {

    /**
     * 过滤范围
     */
    ALL("all", (1 << 10) - 1, "过滤所有"),
    TENANT("tenant", 1 << 1, "过滤租户"),
    ORG("org", 1 << 2, "过滤组织"),
    OTHER("other", 1, "过滤其他");

    /**
     * key
     */
    private final String key;

    /**
     * 值
     */
    private final int value;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param key  值
     * @param desc 描述
     */
    SqlParserIgnoreEnum(String key, int value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }
}
