package com.github.d3.base.dto;

import com.github.d3.base.enums.CredentialsTypeEnum;
import com.github.d3.data.jdbc.dto.MpBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户凭证
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserCredentialsDTO", description = "用户凭证信息")
public class UserCredentialsDTO extends MpBaseDTO {

    @Serial
    private static final long serialVersionUID = -8179076796496627840L;

    /**
     * 凭证
     */
    @Schema(description = "credentials")
    @Size(min = 1, max = 255, message = "")
    private String credentials;

    /**
     * 凭证类型
     */
    @Schema(description = "credentials_type")
    private CredentialsTypeEnum credentialsType;

}
