package com.github.d3.base.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import com.github.d3.enums.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDate;

/**
 * 用户相关信息
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_profile")
public class UserProfileEntity extends MpBaseEntity {

    @Serial
    private static final long serialVersionUID = 3733180786679925375L;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 性别
     */
    @TableField("gender")
    private GenderEnum gender;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 头像地址
     */
    @TableField("avatar")
    private String avatar;
}
