package com.github.d3.enums;

import lombok.Getter;

/**
 * 数据源类型枚举类
 *
 * @author Carzer1020@163.com
 * @since 2023-02-03
 */
@Getter
public enum DatasourceTypeEnum {

    /**
     * 数据源类型
     */
    JDBC(0, "jdbc", "Jdbc数据源"),
    REDIS(1, "redis", "Redis数据源"),
    MONGO(2, "mongo", "MongoDB数据源");

    /**
     * val
     */
    private final Integer val;

    /**
     * key
     */
    private final String key;

    /**
     * 描述
     */
    private final String desc;

    /**
     * @param key  key
     * @param desc 描述
     */
    DatasourceTypeEnum(Integer val, String key, String desc) {
        this.val = val;
        this.key = key;
        this.desc = desc;
    }
}
