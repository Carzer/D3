package com.github.d3.code;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回码(1_000-9_999)
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
@Getter
public enum RCode implements D3Code {

    /**
     * 操作码
     */
    SUCCESS(1_000L, "操作成功!"),
    FAILED(1_001L, "操作失败!"),
    VALIDATE_FAILED(1_002L, "参数校验失败!"),
    VALID_CODE_EMPTY(1_003L, "验证码不能为空!"),
    VALID_CODE_EXPIRED(1_004L, "验证码已过期!"),
    VALID_CODE_ERROR(1_005L, "验证码错误!"),
    TOKEN_EXPIRED(1_006L, "token已过期!"),
    UNAUTHORIZED(1_007L, "未授权!"),
    FORBIDDEN(1_008L, "权限不足，请联系管理员!"),
    BAD_CREDENTIALS(1_009L, "用户名或密码错误!"),
    ROLE_EMPTY(1_010L, "当前用户没有配置角色，请联系管理员!"),
    USER_LOCKED(1_011L, "用户已锁定，请联系管理员!"),
    USER_EXPIRED(1_012L, "用户已过期，请联系管理员!"),
    LOGIN_NAME_EXISTED(1_013L, "登录名已存在!"),
    CODE_EXISTED(1_014L, "编码重复!"),
    OUT_OF_BOUNDS(1_015L, "超出权限范围(0-25)!"),
    BIT_DIGIT_EXISTED(1_016L, "位移值重复!"),
    MENU_NOT_EXISTED(1_017L, "菜单不存在!"),
    METHOD_NOT_SUPPORTED(1_018L, "不支持的方法!"),
    CODE_GENERATE_ERROR(1_019L, "代码生成错误!"),
    PK_NOT_EXISTED(1_020L, "主键不存在!"),
    DB_TYPE_NOT_SUPPORTED(1_021L, "不支持的数据库类型!"),
    PHONE_EXISTED(1_022L, "手机号已存在!"),
    USER_CREATE_FAILED(1_023L, "创建用户失败!"),
    USER_TENANT_EXISTED(1_024L, "用户已经加入租户!"),
    TENANT_INFO_ERROR(1_025L, "租户信息异常或未加入租户!"),
    USER_NOT_EXISTED(1_026L, "用户不存在!"),
    ORG_NOT_EXISTED(1_027L, "组织不存在!"),
    ILLEGAL_OPERATION(4_998L, "非法操作!"),
    SYSTEM_BUSY(4_998L, "系统繁忙，请稍后尝试!"),
    SYSTEM_ERROR(4_999L, "系统异常，请联系管理员!"),
    ERROR(5_000L, "未知错误");

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
    RCode(Long code, String message) {
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