package com.github.d3.base;

import com.github.d3.base.dto.UserDTO;
import com.github.d3.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 登录授权结果
 *
 * @author Carzer1020@163.com
 * @since 2024-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AResult implements BaseDTO {

    @Serial
    private static final long serialVersionUID = -5302773733994146542L;

    /**
     * 消息ID
     */
    private String id;

    private UserDTO user;


}
