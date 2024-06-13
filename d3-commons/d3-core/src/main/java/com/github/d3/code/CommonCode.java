package com.github.d3.code;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用编码(1_000-9_999)
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum CommonCode implements RCode {

    /**
     * 操作码
     */
    SUCCESS(1_000L, "操作成功!"),
    FAILED(1_001L, "操作失败!"),
    UNAUTHORIZED(1_002L, "未授权!"),
    FORBIDDEN(1_003L, "权限不足，请联系管理员!"),
    METHOD_NOT_SUPPORTED(1_004L, "不支持的方法!"),
    DATASOURCE_TYPE_NOT_SUPPORTED(1_005L, "不支持的数据源类型!"),

    CODE_GENERATE_ERROR(1_006L, "代码生成错误!"),

    ILLEGAL_OPERATION(4_998L, "非法操作!"),
    SYSTEM_BUSY(4_998L, "系统繁忙，请稍后尝试!"),
    SYSTEM_ERROR(4_999L, "系统异常，请联系管理员!"),
    ERROR(5_000L, "未知错误");

    /**
     * 所有枚举集合
     */
    private static final Map<Long, CommonCode> ENUMS = new HashMap<>();

    /*
     * 静态方法
     */
    static {
        for (CommonCode commonCode : CommonCode.values()) {
            ENUMS.put(commonCode.getCode(), commonCode);
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
    CommonCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据编码获取枚举
     *
     * @param code 编码
     * @return 枚举
     */
    public static CommonCode getEnum(Long code) {
        return ENUMS.get(code);
    }
}