package com.github.d3.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * 注解配置权限相关配置
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Order
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = false)
@Configuration
public class MethodSecurityConfig {

}
