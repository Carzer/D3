package com.github.d3.auth.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户信息
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_user")
public class UserEntity extends MpBaseEntity {

    @Serial
    private static final long serialVersionUID = -2694881190589983175L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 登录名
     */
    @TableField(value = "login_name", updateStrategy = FieldStrategy.NEVER)
    private String loginName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

}
