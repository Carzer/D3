package com.github.d3.data.jdbc.constants;

/**
 * 逻辑删除常量
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public final class JdbcDataBaseConstants {

    /**
     * ID字段
     */
    public static final String ID = "id";

    /**
     * 更新人字段
     */
    public static final String UPDATE_BY_PROPERTY = "updateBy";

    /**
     * 更新人列
     */
    public static final String UPDATE_BY_COLUMN = "update_by";

    /**
     * 更新人ID字段
     */
    public static final String UPDATE_BY_ID_PROPERTY = "updateById";

    /**
     * 更新人ID列
     */
    public static final String UPDATE_BY_ID_COLUMN = "update_by_id";

    /**
     * 更新时间字段
     */
    public static final String UPDATE_DATE_PROPERTY = "updateTime";

    /**
     * 更新时间字段
     */
    public static final String UPDATE_DATE_COLUMN = "update_time";

    /**
     * 删除标识列
     */
    public static final String DELETED_COLUMN = "deleted";

    /**
     * 创建人字段
     */
    public static final String CREATE_BY_PROPERTY = "createBy";

    /**
     * 创建人列
     */
    public static final String CREATE_BY_COLUMN = "create_by";

    /**
     * 创建人ID字段
     */
    public static final String CREATE_BY_ID_PROPERTY = "createById";

    /**
     * 创建人ID列
     */
    public static final String CREATE_BY_ID_COLUMN = "create_by_id";

    /**
     * 创建时间字段
     */
    public static final String CREATE_DATE_PROPERTY = "createTime";

    /**
     * 创建时间列
     */
    public static final String CREATE_DATE_COLUMN = "create_time";

    /**
     * 乐观锁字段
     */
    public static final String VERSION = "version";

    /**
     * 乐观锁字段填充内容
     */
    public static final String VERSION_FILL = "%s+1";

    /**
     * 未逻辑删除的值
     */
    public static final String LOG_NON_DELETE_VAL = "0";

    /**
     * 私有构造方法
     */
    private JdbcDataBaseConstants() {
        throw new UnsupportedOperationException();
    }
}