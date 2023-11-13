package com.github.d3.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.List;

/**
 * 查询结果
 *
 * @author Carzer1020@163.com
 * @since 2021-04-06
 */
@Getter
@Setter
@ToString
@Schema(description = "查询结果封装")
public final class PageResult<T> implements PageAble {

    @Serial
    private static final long serialVersionUID = 5523723317162045300L;

    /**
     * 起始页码
     */
    @Schema(description = "起始页码")
    private Long pageNum;

    /**
     * 每页记录数
     */
    @Schema(description = "每页记录数")
    private Long pageSize;

    /**
     * 所有记录数
     */
    @Schema(description = "所有记录数")
    private Long total;

    /**
     * 所有页数
     */
    @Schema(description = "所有页数")
    private Long pages;

    /**
     * 查询结果
     */
    @Schema(description = "查询结果")
    private List<T> records;
}
