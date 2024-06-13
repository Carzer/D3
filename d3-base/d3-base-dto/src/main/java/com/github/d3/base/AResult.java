package com.github.d3.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.d3.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.io.Serial;
import java.util.Collection;

/**
 * 登录授权结果
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema
public class AResult implements BaseDTO {

    @Serial
    private static final long serialVersionUID = -5302773733994146542L;

    /**
     * ID
     */
    @Schema(description = "ID")
    @JsonIgnore
    private String id;

    /**
     * 用户信息
     */
    @Schema(description = "用户信息")
    private Object principal;

    /**
     * 权限信息
     */
    @Schema(description = "权限信息")
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * token信息
     */
    @Schema(description = "token信息")
    private OAuth2AccessToken token;

}
