package com.github.d3.base.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.base.enums.AccountTypeEnum;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户账号相关信息
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_account")
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

    /**
     * 是否启用登录
     */
    @TableField("enabled")
    private Boolean enabled;

}
