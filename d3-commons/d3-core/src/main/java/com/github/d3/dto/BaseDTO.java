package com.github.d3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 基础DTO
 *
 * @author Carzer1020@163.com
 * @since 2019-07-17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface BaseDTO extends Serializable {

    /**
     * 获取ID
     *
     * @return ID
     */
    Serializable getId();
}
