package com.github.d3.data.jdbc.constants;

/**
 * 逻辑删除常量
 *
 * @author Carzer1020@163.com
 * @since 2022-01-10
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
     * 更新人字段
     */
    public static final String UPDATE_BY_COLUMN = "update_by";

    /**
     * 更新时间字段
     */
    public static final String UPDATE_DATE_PROPERTY = "updateDate";

    /**
     * 更新时间字段
     */
    public static final String UPDATE_DATE_COLUMN = "update_date";

    /**
     * 删除人字段
     */
    public static final String DELETE_BY_PROPERTY = "deleteBy";

    /**
     * 删除人列
     */
    public static final String DELETE_BY_COLUMN = "delete_by";

    /**
     * 删除时间列
     */
    public static final String DELETE_DATE_COLUMN = "delete_date";

    /**
     * 删除标识列
     */
    public static final String DELETE_FLAG_COLUMN = "delete_flag";

    /**
     * 创建人字段
     */
    public static final String CREATE_BY_PROPERTY = "createBy";

    /**
     * 创建人列
     */
    public static final String CREATE_BY_COLUMN = "create_by";

    /**
     * 创建时间字段
     */
    public static final String CREATE_DATE_PROPERTY = "createDate";

    /**
     * 创建时间列
     */
    public static final String CREATE_DATE_COLUMN = "create_date";

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
     * 逻辑删除时的填充内容
     */
    public static final String LOGIC_DELETE_FILL =
            ID + ", " + DELETE_DATE_COLUMN + " = now(), " + DELETE_BY_COLUMN + " = '" + DELETE_BY_PROPERTY + "'";

    /**
     * 私有构造方法
     */
    private JdbcDataBaseConstants() {
        throw new UnsupportedOperationException();
    }
}