package com.github.d3.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.auth.enums.AccountTypeEnum;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户账号相关信息
 *
 * @author wanghongqun
 * @since 2023-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_user_account")
public class UserAccountEntity extends MpBaseEntity {

    @Serial
    private static final long serialVersionUID = -8176348112905793974L;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 账号类型
     */
    @TableField("account_type")
    private AccountTypeEnum accountType;
}
