package com.github.d3.data.jdbc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.d3.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * Mybatis Plus 基础DTO
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
@Data
public class MpBaseDTO implements BaseDTO {

    @Serial
    private static final long serialVersionUID = 8068207901131016867L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDate;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateDate;

    /**
     * 版本号
     */
    @Schema(description = "版本号")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer version;
}
