package com.github.d3.data.jdbc.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.d3.data.jdbc.interceptor.FillLineInterceptor;
import com.github.d3.data.jdbc.interceptor.LogicDeleteInterceptor;
import com.github.d3.data.jdbc.property.MpDataControlProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * mybatis-plus相关配置
 *
 * @author Carzer1020@163.com
 * @since 2020-11-19
 */
@SuppressWarnings("all")
@EnableTransactionManagement
@Configuration
@MapperScan("com.github.d3.**.mapper")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MpConfiguration {

    /**
     * 逻辑删除插件
     */
    private final LogicDeleteInterceptor logicDeleteInterceptor;

    /**
     * 自定义配置
     */
    private final MpDataControlProperties properties;

    /**
     * 自动填充列
     */
    private Map<String, FillLineInterceptor> fillLineInterceptors;

    /**
     * 设置自动填充列
     *
     * @param fillLineInterceptors 自动填充列
     */
    @Autowired(required = false)
    public void setFillLineInterceptors(Map<String, FillLineInterceptor> fillLineInterceptors) {
        this.fillLineInterceptors = fillLineInterceptors;
    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 设置数据权限控制器
        dataControl(interceptor);
        // 设置逻辑删除插件
        interceptor.addInnerInterceptor(logicDeleteInterceptor);
        // 自动填充列
        if (!CollectionUtils.isEmpty(fillLineInterceptors)) {
            fillLineInterceptors.values().forEach(interceptor::addInnerInterceptor);
        }
        // 分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor());
        // 乐观锁插件
        interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        // 防止全表更新与删除插件
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor());
        return interceptor;
    }

    /**
     * 分页插件
     *
     * @return 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setOverflow(false);
        return paginationInnerInterceptor;
    }

    /**
     * 乐观锁插件
     *
     * @return 乐观锁插件
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 防止全表更新与删除插件
     *
     * @return 防止全表更新与删除插件
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }

    /**
     * ID生成器
     *
     * @return 雪花算法ID生成
     */
    @Bean
    public DefaultIdentifierGenerator defaultIdentifierGenerator() {
        return new DefaultIdentifierGenerator();
    }

    /**
     * 设置数据权限控制器
     *
     * @param interceptor interceptor
     */
    private void dataControl(MybatisPlusInterceptor interceptor) {
        // 设置数据权限控制器
        if (properties.isEnable()) {
            if (log.isDebugEnabled()) {
                log.debug("开启数据权限控制...");
            }
//            interceptor.addInnerInterceptor(dataScopeInterceptor);
        }
    }
}
