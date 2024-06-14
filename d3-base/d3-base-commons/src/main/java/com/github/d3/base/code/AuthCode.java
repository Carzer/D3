package com.github.d3.base.code;

import com.github.d3.code.RCode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 授权、鉴权相关编码(10_000-19_999)
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum AuthCode implements RCode {

    VALIDATE_FAILED(10_002L, "参数校验失败!"),
    VALID_CODE_EMPTY(10_003L, "验证码不能为空!"),
    VALID_CODE_EXPIRED(10_004L, "验证码已过期!"),
    VALID_CODE_ERROR(10_005L, "验证码错误!"),
    TOKEN_EXPIRED(10_006L, "token已过期!"),
    BAD_CREDENTIALS(10_009L, "用户名或密码错误!"),
    USER_LOCKED(10_011L, "用户已锁定，请联系管理员!"),
    USER_EXPIRED(10_012L, "用户已过期，请联系管理员!"),
    LOGIN_ACCOUNT_EXISTED(10_013L, "登录账号已存在!"),
    CODE_EXISTED(10_014L, "编码重复!"),
    OUT_OF_BOUNDS(10_015L, "超出权限范围!"),
    BIT_DIGIT_EXISTED(10_016L, "位移值重复!"),
    MENU_NOT_EXISTED(10_017L, "菜单不存在!"),
    PHONE_EXISTED(10_022L, "手机号已存在!"),
    USER_CREATE_FAILED(10_023L, "创建用户失败!"),
    USER_NOT_EXISTED(10_026L, "用户不存在!"),
    ORG_NOT_EXISTED(10_027L, "组织不存在!");

    /**
     * 所有枚举集合
     */
    private static final Map<Long, AuthCode> ENUMS = new HashMap<>();

    /*
     * 静态方法
     */
    static {
        for (AuthCode authCode : AuthCode.values()) {
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
    AuthCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据编码获取枚举
     *
     * @param code 编码
     * @return 枚举
     */
    public static AuthCode getEnum(Long code) {
        return ENUMS.get(code);
    }
}
