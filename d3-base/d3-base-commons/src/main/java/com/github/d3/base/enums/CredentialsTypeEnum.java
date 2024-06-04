package com.github.d3.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 凭证类型
 *
 * @author Carzer1020@163.com
 * @since 2023-12-07
 */
@Getter
public enum CredentialsTypeEnum {

    /**
     * 凭证类型
     */
    PASSWORD(0, "password");

    /**
     * 枚举map
     */
    private static final Map<Integer, CredentialsTypeEnum> ENUMS = new HashMap<>();

    /*
     * 静态方法
     */
    static {
        for (CredentialsTypeEnum value : CredentialsTypeEnum.values()) {
            ENUMS.put(value.getKey(), value);
        }
    }

    /**
     * 构造方法
     *
     * @param key  key
     * @param desc desc
     */
    CredentialsTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * key
     */
    @EnumValue
    @JsonValue
    private final Integer key;

    /**
     * desc
     */
    private final String desc;

    /**
     * 获取凭证类型
     *
     * @param key key
     * @return 凭证类型
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static CredentialsTypeEnum of(Integer key) {
        return ENUMS.get(key);
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return this.key == null ? null : this.key.toString();
    }
}
