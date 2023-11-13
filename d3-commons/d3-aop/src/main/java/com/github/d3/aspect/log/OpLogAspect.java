package com.github.d3.aspect.log;

import com.github.d3.annotations.log.OpLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 定义日志处理类
 *
 * @author Carzer1020@163.com
 * @since 2019-12-23
 */
@Aspect
@Component
@Order(-5)
@Slf4j
public class OpLogAspect {

    /**
     * 日志记录类
     */
    private LogRecordProvider logRecordProvider;

    /**
     * 日志记录类 注入方法
     *
     * @param logRecordProvider 日志记录类
     */
    @Autowired
    public void setLogRecordProvider(LogRecordProvider logRecordProvider) {
        this.logRecordProvider = logRecordProvider;
    }

    /**
     * 定义切入点拦截规则，拦截{@link OpLog}注解的方法
     */
    @Pointcut("@annotation(com.github.d3.annotations.log.OpLog)")
    public void logPointCut() {
        // 标记方法
    }

    /**
     * 记录操作日志
     */
    @AfterReturning(value = "logPointCut()", returning = "object")
    public void doAfterReturning(JoinPoint joinPoint, Object object) {
        // 获取请求的ip
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        logRecordProvider.execute(request, joinPoint, object, null);
    }

    /**
     * 异常处理
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "cause")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable cause) {
        // 获取请求的ip
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        logRecordProvider.execute(request, joinPoint, null, cause);
    }

    /**
     * 如果没有实现日志记录类，则默认打印到控制台
     *
     * @return 日志记录类
     */
    @Bean
    @ConditionalOnMissingBean
    public LogRecordProvider logRecordProvider() {
        return (HttpServletRequest request, JoinPoint joinPoint, Object object, Throwable cause) -> {
            try {
                // 从切面织入点处通过反射机制获取织入点处的方法
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                // 获取切入点所在的方法
                Method method = signature.getMethod();
                // 获取请求的类名
                String className = joinPoint.getTarget().getClass().getName();
                // 获取请求的方法名
                String methodName = method.getName();
                // 获取操作
                OpLog opLog = method.getAnnotation(OpLog.class);
                // 操作说明
                String value = opLog.value();
                // 详细描述
                String description = opLog.description();
                // 用户名
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                // 获取方法执行前时间
                LocalDateTime date = LocalDateTime.now();
                String url = request.getRequestURI();
                Object result;
                if (cause != null) {
                    result = cause.getMessage();
                } else {
                    result = object;
                }
                OpLogAspect.log.info("username:{},url:{},className:{},methodName:{},value:{},description:{},operationDate:{},result:{}",
                        userName, url, className, methodName, value, description, date, result);
            } catch (Exception e) {
                log.error("获取操作日志异常：{}", e.getMessage());
            }
        };
    }
}
