package com.github.d3.constants;


import lombok.experimental.UtilityClass;

/**
 * 通用常量
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@UtilityClass
public class CommonConstants {

    /**
     * 路由key
     */
    public final String ROUTE_KEY = "routes:";

    /**
     * buffer len
     */
    public final Integer READ_BUFFER_LEN = 100;

    /**
     * 数据权限字段
     */
    public final String SYSTEM_DATA_SCOPE_KEY = "data_scope";

    /**
     * request header中，用于判断请求来源的字段
     */
    public final String HEADER_SOURCE = "Source";

    /**
     * 加密标识
     */
    public final String HEADER_ALGORITHM = "Algorithm";

    /**
     * 外部系统加密请求头的前缀
     * 例[Algorithm = client:sm4]
     */
    public final String CLIENT_PREFIX = "client:";

    /**
     * 不过滤内容标识
     */
    public final String NO_TRIM_HEADER = "No-Trim";

    /**
     * unknown标识
     */
    public final String UNKNOWN = "unknown";

    /**
     * 用户代理
     */
    public final String USER_AGENT = "User-Agent";

    /**
     * 默认 sm4加密key
     */
    public final String DEFAULT_SM4_VERIFY_KEY = "GFRAMNFJRDYKKZFM";

    /**
     * token sm4加密key
     */
    public final String TOKEN_SM4_VERIFY_KEY = "TJXOBREMGMDLCLFS";

    /**
     * 根编码 36进制
     */
    public final String ROOT_NUM = "1000";

    /**
     * 根组织父编码 36进制
     */
    public final String ROOT_PARENT_NUM = "0000";

    /**
     * 零号租户ID
     */
    public final Long ZERO_TENANT_ID = 0L;

}
