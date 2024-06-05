package com.github.d3.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 动作
 *
 * @author Carzer1020@163.com
 * @since 2024-05-23
 */
@TableName("action")
@Data
@EqualsAndHashCode(callSuper = false)
public class Action extends MpBaseEntity {

    @Serial
    private static final long serialVersionUID = 885167609709634089L;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 动作值
     */
    @TableField("val")
    private Long val;

    /**
     * 模块ID
     */
    @TableField("module_id")
    private Long moduleId;
}
