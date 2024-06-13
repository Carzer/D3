package com.github.d3.data.jdbc.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义属性
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "d3.data.mp", ignoreInvalidFields = true)
public class MpDataControlProperties {

    /**
     * 是否开启数据权限控制(组织ID)
     */
    private boolean enable = false;

    /**
     * 组织handler需要过滤的表
     */
    private List<String> ignoreTables = new ArrayList<>();
}
