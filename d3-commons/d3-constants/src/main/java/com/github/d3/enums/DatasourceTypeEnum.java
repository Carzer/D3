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
    JDBC("jdbc", "Jdbc数据源");

    /**
     * key
     */
    private String key;

    /**
     * 描述
     */
    private String desc;

    /**
     * @param key  key
     * @param desc 描述
     */
    DatasourceTypeEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
