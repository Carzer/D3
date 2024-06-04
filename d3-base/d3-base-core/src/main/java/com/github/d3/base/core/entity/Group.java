package com.github.d3.base.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 组信息
 *
 * @author Carzer1020@163.com
 * @since 2024-05-23
 */
@TableName("group")
@Data
@EqualsAndHashCode(callSuper = false)
public class Group extends MpBaseEntity {

    @Serial
    private static final long serialVersionUID = -8342445145063783188L;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 父编码
     */
    @TableField("p_code")
    private String pCode;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 是否私有
     */
    @TableField("pvt")
    private Boolean pvt;
}
