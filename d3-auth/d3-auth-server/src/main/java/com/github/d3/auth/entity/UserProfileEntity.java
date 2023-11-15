package com.github.d3.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.enums.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户相关信息
 *
 * @author wanghongqun
 * @since 2023-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_user_profile")
public class UserProfileEntity {

    @TableField("user_id")
    private Long userId;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("gender")
    private GenderEnum gender;
}
