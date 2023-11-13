package com.github.d3.code;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回码
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
public enum RCode implements DmsCode {

    /**
     * 操作码
     */
    SUCCESS(1_000, "操作成功!"),
    FAILED(1_001, "操作失败!"),
    VALIDATE_FAILED(1_002, "参数校验失败!"),
    VALID_CODE_EMPTY(1_003, "验证码不能为空!"),
    VALID_CODE_EXPIRED(1_004, "验证码已过期!"),
    VALID_CODE_ERROR(1_005, "验证码错误!"),
    TOKEN_EXPIRED(1_006, "token已过期!"),
    UNAUTHORIZED(1_007, "未授权!"),
    FORBIDDEN(1_008, "权限不足，请联系管理员!"),
    BAD_CREDENTIALS(1_009, "用户名或密码错误!"),
    ROLE_EMPTY(1_010, "当前用户没有配置角色，请联系管理员!"),
    USER_LOCKED(1_011, "用户已锁定，请联系管理员!"),
    USER_EXPIRED(1_012, "用户已过期，请联系管理员!"),
    LOGIN_NAME_EXISTED(1_013, "登录名已存在!"),
    CODE_EXISTED(1_014, "编码重复!"),
    OUT_OF_BOUNDS(1_015, "超出权限范围(0-25)!"),
    BIT_DIGIT_EXISTED(1_016, "位移值重复!"),
    MENU_NOT_EXISTED(1_017, "菜单不存在!"),
    METHOD_NOT_SUPPORTED(1_018, "不支持的方法!"),
    CODE_GENERATE_ERROR(1_019, "代码生成错误!"),
    PK_NOT_EXISTED(1_020, "主键不存在!"),
    DB_TYPE_NOT_SUPPORTED(1_021, "不支持的数据库类型!"),
    PHONE_EXISTED(1_022, "手机号已存在!"),
    USER_CREATE_FAILED(1_023, "创建用户失败!"),
    USER_TENANT_EXISTED(1_024, "用户已经加入租户!"),
    TENANT_INFO_ERROR(1_025, "租户信息异常或未加入租户!"),
    USER_NOT_EXISTED(1_026, "用户不存在!"),
    ORG_NOT_EXISTED(1_027, "组织不存在!"),
    ILLEGAL_OPERATION(4_998, "非法操作!"),
    SYSTEM_BUSY(4_998, "系统繁忙，请稍后尝试!"),
    SYSTEM_ERROR(4_999, "系统异常，请联系管理员!"),
    ERROR(5_000, "未知错误");

    /**
     * 所有枚举集合
     */
    private static final Map<Long, RCode> ENUMS = new HashMap<>();

    /*
     * 静态方法
     */
    static {
        for (RCode rCode : RCode.values()) {
            ENUMS.put(rCode.getCode(), rCode);
        }
    }

    /**
     * 编码
     */
    @Getter
    private final long code;

    /**
     * 信息
     */
    @Getter
    private final String message;

    /**
     * 构造方法
     *
     * @param code    编码
     * @param message 信息
     */
    RCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据编码获取枚举
     *
     * @param code 编码
     * @return 枚举
     */
    public static RCode getEnum(Long code) {
        return ENUMS.get(code);
    }
}