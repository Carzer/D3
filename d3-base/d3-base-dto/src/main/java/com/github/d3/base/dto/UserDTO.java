package com.github.d3.base.dto;

import com.github.d3.base.enums.UserTypeEnum;
import com.github.d3.data.jdbc.dto.MpBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserDTO", description = "用户信息")
public class UserDTO extends MpBaseDTO {

    @Serial
    private static final long serialVersionUID = -3569077309970490430L;

    /**
     * 姓名
     */
    @Schema(description = "name")
    @Size(min = 1, max = 50)
    private String name;

    /**
     * 编码
     */
    @Schema(description = "code")
    @Size(min = 1, max = 255)
    private String uid;

    /**
     * 用户类型
     */
    @Schema(description = "user_type")
    private UserTypeEnum userType;

    /**
     * 手机号
     */
    @Schema(description = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "email")
    private String email;


    /**
     * 是否锁定
     */
    @Schema(description = "locked")
    private Boolean locked;

    /**
     * 过期时间
     */
    @Schema(description = "expire_at")
    private LocalDateTime expireAt;

}
