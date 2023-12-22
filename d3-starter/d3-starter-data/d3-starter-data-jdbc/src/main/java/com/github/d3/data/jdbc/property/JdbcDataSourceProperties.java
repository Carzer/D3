package com.github.d3.data.jdbc.property;

import com.github.d3.data.jdbc.JdbcDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 *
 * @author Carzer1020@163.com
 * @since 2019-12-13
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource", ignoreInvalidFields = true)
public class JdbcDataSourceProperties {

    /**
     * 动态多数据源
     */
    private Map<String, JdbcDataSource> jdbc = new HashMap<>();

}