package com.github.d3.aspect.datasource;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.d3.annotations.datasource.DataSource;
import com.github.d3.code.RCode;
import com.github.d3.enums.DatasourceTypeEnum;
import com.github.d3.exception.D3Exception;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

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
     * 数据源缓存
     */
    private final Cache<DatasourceTypeEnum, DynamicDataSourceProvider> providerCache = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofHours(1)).maximumSize(100).build();

    @SneakyThrows
    @Around("@within(dataSource) || @annotation(dataSource)")
    public Object around(ProceedingJoinPoint point, DataSource dataSource) {
        // 先判断 dataSource 是否为空, 为空则尝试获取上一级注解
        if (dataSource == null) {
            Class<?> clazz = point.getTarget().getClass();
            dataSource = AnnotationUtils.findAnnotation(clazz, DataSource.class);
        }

        // 如果数据源配置信息异常，就不再执行后续操作
        if (dataSource == null) {
            throw new D3Exception(RCode.ERROR);
        }

        // 获取数据源名称及类型
        String dataSourceName = dataSource.value();
        DatasourceTypeEnum dataSourceType = dataSource.type();

        // 获取对应的数据源provider，执行对应的before及after方法
        DynamicDataSourceProvider provider = getProvider(dataSourceType);
        provider.before(dataSourceName);
        Object result = point.proceed();
        provider.after(dataSourceName);
        return result;
    }

    /**
     * 获取provider
     *
     * @param type 数据源类型
     * @return provider
     */
    private DynamicDataSourceProvider getProvider(DatasourceTypeEnum type) {
        if (type == null) {
            throw new D3Exception(RCode.DB_TYPE_NOT_SUPPORTED);
        }
        return providerCache.get(type, key ->
                dynamicDataSourceProviders
                        .stream()
                        .filter(provider ->
                                provider.supports(key)
                        ).findFirst()
                        .orElseThrow(
                                () -> new D3Exception(RCode.DB_TYPE_NOT_SUPPORTED)
                        )
        );
    }

}