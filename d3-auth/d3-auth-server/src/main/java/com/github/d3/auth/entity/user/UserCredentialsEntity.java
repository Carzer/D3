package com.github.d3.auth.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.d3.auth.enums.CredentialsTypeEnum;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户凭证
 *
 * @author Carzer1020@163.com
 * @since 2023-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_credentials")
public class UserCredentialsEntity extends MpBaseEntity {

    @Serial
    private static final long serialVersionUID = -6402840273577104927L;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 凭证
     */
    @TableField("credentials")
    private String credentials;

    /**
     * 凭证类型
     */
    @TableField("credentials_type")
    private CredentialsTypeEnum credentialsType;

}
