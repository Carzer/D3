package com.github.d3.auth.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.auth.enums.UserTypeEnum;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class UserEntity extends MpBaseEntity {

    @Serial
    private static final long serialVersionUID = -2694881190589983175L;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 唯一标识(很多系统里面喜欢使用用户编码)
     */
    @TableField("uid")
    private String uid;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private UserTypeEnum userType;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 是否锁定
     */
    @TableField("locked")
    private Boolean locked;

    /**
     * 过期时间
     */
    @TableField("expire_at")
    private LocalDateTime expireAt;

    /**
     * 凭证
     */
    @TableField(exist = false)
    private String credentials;

    /**
     * 用户相关信息
     */
    @TableField(exist = false)
    private UserProfileEntity userProfile;
}
