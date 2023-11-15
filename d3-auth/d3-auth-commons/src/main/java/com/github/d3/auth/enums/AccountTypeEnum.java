package com.github.d3.auth.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户账户类型枚举类
 *
 * @author wanghongqun
 * @since 2023-11-14
 */
@Getter
public enum AccountTypeEnum implements Serializable {

    /**
     * 账号类型
     */
    NORMAL(0,"普通"),
    PHONE(1,"电话"),
    EMAIL(2,"邮箱");

    /**
     * 枚举类map
     */
    private static final Map<Integer, AccountTypeEnum> ENUMS = new HashMap<>();

    /*
     * 静态方法
     */
    static {
        for (AccountTypeEnum value : AccountTypeEnum.values()) {
            ENUMS.put(value.getKey(), value);
        }
    }

    /**
     * 构造方法
     * @param key key
     * @param desc 描述
     */
     AccountTypeEnum(int key,String desc){
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
     * 描述
     */
    private final String desc;

    /**
     * 获取账号类型
     * @param key key
     * @return 账号类型
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static AccountTypeEnum of(Integer key) {
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
