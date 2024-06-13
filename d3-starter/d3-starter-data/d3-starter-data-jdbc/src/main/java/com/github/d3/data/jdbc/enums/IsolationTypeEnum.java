package com.github.d3.data.jdbc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统隔离类型
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum IsolationTypeEnum implements Serializable {

    /**
     * 类型
     */
    SYSTEM(0, "系统"),
    TENANT(10, "租户"),
    NORMAL(100, "普通");

    /**
     * 隔离类型枚举map
     */
    private static final Map<Integer, IsolationTypeEnum> ENUMS = new HashMap<>();

    /*
     * 静态方法
     */
    static {
        for (IsolationTypeEnum isolationTypeEnum : IsolationTypeEnum.values()) {
            ENUMS.put(isolationTypeEnum.getType(), isolationTypeEnum);
        }
    }

    /**
     * 类型
     */
    @EnumValue
    @JsonValue
    private final Integer type;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param type 类型
     * @param desc 描述
     */
    IsolationTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 获取隔离类型
     *
     * @param key key
     * @return 隔离类型
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static IsolationTypeEnum of(Integer key) {
        return ENUMS.get(key);
    }

    /**
     * toString方法
     *
     * @return toString
     */
    @Override
    public String toString() {
        return this.type == null ? null : this.type.toString();
    }
}
