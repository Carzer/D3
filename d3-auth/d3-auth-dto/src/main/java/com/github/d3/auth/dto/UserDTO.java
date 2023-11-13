package com.github.d3.auth.dto;

import com.github.d3.data.jdbc.dto.MpBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
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
@Schema(name = "UserDTO", description = "用户信息")
public class UserDTO extends MpBaseDTO {

    @Serial
    private static final long serialVersionUID = -3569077309970490430L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Size(min = 1, max = 50)
    private String name;

    /**
     * 登录名
     */
    @Schema(description = "登录名")
    @Size(min = 1, max = 50)
    private String loginName;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

}
