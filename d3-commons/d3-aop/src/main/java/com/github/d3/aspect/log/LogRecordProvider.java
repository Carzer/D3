package com.github.d3.aspect.log;

import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;

/**
 * 日志记录
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@FunctionalInterface
public interface LogRecordProvider {

    /**
     * 记录日志
     *
     * @param request   访问请求
     * @param joinPoint 切入点
     * @param object    信息
     * @param cause     异常
     */
    @SneakyThrows
    void execute(HttpServletRequest request, JoinPoint joinPoint, Object object, Throwable cause);
}
