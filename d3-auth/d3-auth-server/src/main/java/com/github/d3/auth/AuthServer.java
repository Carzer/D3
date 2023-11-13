package com.github.d3.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 授权服务
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.github.d3")
public class AuthServer {

    public static void main(String[] args) {
        SpringApplication.run(AuthServer.class, args);
        String repeat = "=".repeat(20);
        log.warn("{} 授权中心启动成功 {}", repeat, repeat);
    }

    /**
     * http请求模板
     *
     * @return http请求模板
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}