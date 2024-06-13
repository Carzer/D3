package com.github.d3.data.commons.code;

import com.github.d3.code.RCode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据功能编码(20_000-29_999)
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum DataCode implements RCode {

    PK_NOT_EXISTED(20_000L, "主键不存在!");

    /**
     * 所有枚举集合
     */
    private static final Map<Long, DataCode> ENUMS = new HashMap<>();

    /*
     * 静态方法
     */
    static {
        for (DataCode authCode : DataCode.values()) {
            ENUMS.put(authCode.getCode(), authCode);
        }
    }

    /**
     * 编码
     */
    private final Long code;

    /**
     * 信息
     */
    private final String message;

    /**
     * 构造方法
     *
     * @param code    编码
     * @param message 信息
     */
    DataCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据编码获取枚举
     *
     * @param code 编码
     * @return 枚举
     */
    public static DataCode getEnum(Long code) {
        return ENUMS.get(code);
    }
}
