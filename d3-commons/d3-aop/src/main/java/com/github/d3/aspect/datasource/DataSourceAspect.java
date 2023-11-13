package com.github.d3.aspect.datasource;

import com.github.d3.annotations.datasource.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

/**
 * 数据源AOP切面定义
 *
 * @author Carzer1020@163.com
 * @see DataSource
 * @since 2022-12-13
 */
@Component
@Aspect
@Slf4j
@ConditionalOnBean(DynamicDataSourceProvider.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataSourceAspect {

    /**
     * 动态数据源切换Provider
     */
    private final List<DynamicDataSourceProvider> dynamicDataSourceProviders;

    /**
     * AOP切面的切入点（使用注解的方法）
     */
    @Pointcut("@annotation(com.github.d3.annotations.datasource.DataSource)")
    public void dataSourcePointCut() {
        // 标记方法
    }

    /**
     * 在方法执行前切换数据源
     *
     * @param joinPoint 切入点
     */
    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        DataSource dataSource = dataSource(joinPoint);
        if (dataSource != null) {
            getProviderOptional(dataSource)
                    .ifPresentOrElse(provider ->
                                    provider.before(dataSource.value())
                            , () -> {
                            }
                    );
        }
    }

    /**
     * 执行完切面后，将线程共享中的数据源名称清空
     */
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        DataSource dataSource = dataSource(joinPoint);
        if (dataSource != null) {
            getProviderOptional(dataSource)
                    .ifPresentOrElse(provider ->
                                    provider.after(dataSource.value())
                            , () -> {
                            }
                    );
        }
    }

    /**
     * 获取数据源
     *
     * @param joinPoint 织入点
     * @return 数据源
     */
    private DataSource dataSource(JoinPoint joinPoint) {
        try {
            // 如果方法上存在切换数据源的注解，则根据注解内容进行数据源切换
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取具体内容
            return method.getAnnotation(DataSource.class);
        } catch (Exception e) {
            log.error("get dataSource error: ", e);
            return null;
        }
    }

    /**
     * 获取provider
     *
     * @param dataSource 数据源
     * @return provider
     */
    private Optional<DynamicDataSourceProvider> getProviderOptional(DataSource dataSource) {
        return dynamicDataSourceProviders
                .stream()
                .filter(provider ->
                        provider.supports(dataSource.datasourceType())
                ).findFirst();
    }

}