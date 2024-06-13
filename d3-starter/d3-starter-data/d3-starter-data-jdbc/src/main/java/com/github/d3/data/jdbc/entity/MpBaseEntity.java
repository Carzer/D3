package com.github.d3.data.jdbc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.github.d3.data.jdbc.constants.JdbcDataBaseConstants;
import com.github.d3.entity.BaseEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;


/**
 * Mybatis Plus 基础entity
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Data
@Hidden
public class MpBaseEntity implements BaseEntity {

    @Serial
    private static final long serialVersionUID = 632073124713716935L;

    /**
     * 主键
     */
    @TableId(value = JdbcDataBaseConstants.ID, type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建人
     */
    @TableField(value = JdbcDataBaseConstants.CREATE_BY_COLUMN, fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建人ID
     */
    @TableField(value = JdbcDataBaseConstants.CREATE_BY_ID_COLUMN, fill = FieldFill.INSERT)
    private Long createById;

    /**
     * 创建时间
     */
    @TableField(value = JdbcDataBaseConstants.CREATE_DATE_COLUMN, fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(value = JdbcDataBaseConstants.UPDATE_BY_COLUMN, fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 更新人ID
     */
    @TableField(value = JdbcDataBaseConstants.UPDATE_BY_ID_COLUMN, fill = FieldFill.UPDATE)
    private String updateById;

    /**
     * 更新时间
     */
    @TableField(value = JdbcDataBaseConstants.UPDATE_DATE_COLUMN, fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标记(0:未删除，其他:已删除)
     */
    @TableLogic(value = JdbcDataBaseConstants.LOG_NON_DELETE_VAL)
    @TableField(JdbcDataBaseConstants.DELETED_COLUMN)
    private Long deleted;

    /**
     * 版本号
     */
    @TableField(value = JdbcDataBaseConstants.VERSION, fill = FieldFill.INSERT_UPDATE, update = JdbcDataBaseConstants.VERSION_FILL)
    @Version
    private Integer version;
}
