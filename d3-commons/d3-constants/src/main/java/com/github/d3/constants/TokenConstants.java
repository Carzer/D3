package com.github.d3.constants;

/**
 * 通用常量
 *
 * @author Carzer1020@163.com
 * @since 2019-10-25
 */
public class TokenConstants {

    /**
     * 权限header
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * token开头
     */
    public static final String BEARER_TYPE = "Bearer";

    /**
     * token开头
     */
    public static final String ACCESS_TOKEN = "access_token";

    /**
     * token中租户ID字段
     */
    public static final String TENANT_ID = "tenantId";

    /**
     * token中用户ID字段
     */
    public static final String USER_ID = "userId";

    /**
     * token刷新时间
     */
    public static final String REFRESH_INTERVAL = "refresh_interval";

    /**
     * token有效期
     */
    public static final String EXPIRE_AT = "expireAt";

    /**
     * 加密信息签名key
     */
    public static final String ALGORITHM_SIGN_KEY = "signKey";

    /**
     * 加密信息校验key
     */
    public static final String ALGORITHM_VERIFY_KEY = "verifyKey";

    /**
     * client id
     */
    public static final String CLIENT_ID = "clientId";

    /**
     * 客户端授权方式
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 构造方法
     */
    private TokenConstants() {
        throw new UnsupportedOperationException();
    }
}
