package com.github.d3.springdoc.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义配置
 *
 * @author Carzer1020@163.com
 * @since 2022-11-19
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "d3.springdoc", ignoreInvalidFields = true)
public class SpringDocAutoConfigurationProperties {

    /**
     * api基础包
     */
    private String basePackage;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String description;

    /**
     * 版本
     */
    private String version = "1.0";
}
