package com.github.d3.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * bean工具
 *
 * @author Carzer1020@163.com
 * @since 2020-04-13
 */
@Component
@SuppressWarnings("unchecked")
public final class SpringBeanUtil implements ApplicationContextAware {

    /**
     * 上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * 设置上下文环境
     *
     * @param applicationContext 上下文环境
     */
    private static void setContext(ApplicationContext applicationContext) {
        if (SpringBeanUtil.applicationContext == null) {
            SpringBeanUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 根据类型获取类
     *
     * @param clazz clazz
     * @param <T>   类
     * @return 类实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据类名获取
     *
     * @param name 类名
     * @param <T>  类
     * @return 类实例
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取当前环境参数
     *
     * @return 环境参数
     */
    public static String[] getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }

    /**
     * 设置上下文环境
     *
     * @param applicationContext 上下文环境
     * @throws BeansException exception
     */
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        setContext(applicationContext);
    }
}
