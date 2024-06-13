package com.github.d3.constants;

/**
 * 通用URL
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public final class UrlConstants {

    /**
     * 查询请求
     */
    public static final String GET = "/get";

    /**
     * 分页请求
     */
    public static final String GET_PAGE = "/page";

    /**
     * 插入请求
     */
    public static final String INSERT = "/insert";

    /**
     * 更新请求
     */
    public static final String UPDATE = "/update";

    /**
     * 删除请求
     */
    public static final String DELETE = "/delete";

    /**
     * 构造方法
     */
    private UrlConstants() {
        throw new UnsupportedOperationException();
    }
}
