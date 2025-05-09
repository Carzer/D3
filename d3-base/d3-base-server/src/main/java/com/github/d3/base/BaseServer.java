package com.github.d3.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 基础服务
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.github.d3")
public class BaseServer {

    public static void main(String[] args) {

        // 设置nacos日志及缓存路径
        System.setProperty("JM.LOG.PATH", "logs/base");
        System.setProperty("JM.SNAPSHOT.PATH", "logs/base");
        // 设置nacos日志级别
        System.setProperty("com.alibaba.nacos.naming.log.level", "error");
        System.setProperty("com.alibaba.nacos.config.log.level", "error");

        // 启动项目
        SpringApplication.run(BaseServer.class, args);
        String repeat = "=".repeat(20);
        log.info("{} 基础服务启动成功 {}", repeat, repeat);
    }
}