package com.github.d3.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 分页查询接口
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Schema(description = "查询条件封装")
@Setter
@Getter
public final class PageQuery implements PageAble {

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", fallbackPatterns = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime startDate;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", fallbackPatterns = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime endDate;

    /**
     * 起始页码
     */
    @Schema(description = "起始页码")
    private Long pageNum;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Long pageSize;

    /**
     * 获取起始页码，默认为1
     *
     * @return pageNum
     */
    public Long getPageNum() {
        if (pageNum == null) {
            pageNum = 1L;
        }
        return pageNum;
    }

    /**
     * 获取每页大小，默认为10
     *
     * @return pageSize
     */
    public Long getPageSize() {
        if (pageSize == null) {
            pageSize = 10L;
        }
        return pageSize;
    }

}
