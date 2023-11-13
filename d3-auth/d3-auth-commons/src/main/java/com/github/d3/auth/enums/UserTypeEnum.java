package com.github.d3.auth.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户类型枚举
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
@Getter
public enum UserTypeEnum implements Serializable {

    /**
     * 用户类型
     */
    SUPER_ADMIN(0, "超级管理员"),
    TENANT_ADMIN(100, "租户管理员"),
    NORMAL_USER(1_000, "普通用户");

    /**
     * 用户类型枚举map
     */
    private static final Map<Integer, UserTypeEnum> ENUMS = new HashMap<>();

    /*
     * 静态方法
     */
    static {
        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            ENUMS.put(userTypeEnum.getKey(), userTypeEnum);
        }
    }

    /**
     * key
     */
    @EnumValue
    @JsonValue
    private final Integer key;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param key  key
     * @param desc desc
     */
    UserTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * 获取用户类型
     *
     * @param key key
     * @return 用户类型
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UserTypeEnum of(Integer key) {
        return ENUMS.get(key);
    }

    /**
     * toString方法
     *
     * @return toString
     */
    @Override
    public String toString() {
        return this.key == null ? null : this.key.toString();
    }
}
