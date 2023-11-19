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
 * @since 2020-11-19
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "d3.tenant.mp", ignoreInvalidFields = true)
public class MpTenantControlProperties {

    /**
     * 是否开启租户权限控制
     */
    private boolean enable = false;

    /**
     * 租户handler需要过滤的表
     */
    private List<String> ignoreTables = new ArrayList<>();
}
