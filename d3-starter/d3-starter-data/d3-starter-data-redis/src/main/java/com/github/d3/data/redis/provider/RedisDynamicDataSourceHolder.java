package com.github.d3.data.redis.provider;


import lombok.extern.slf4j.Slf4j;

/**
 * redis数据源切换
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Slf4j
public class RedisDynamicDataSourceHolder {

    /**
     * 本地线程共享对象
     */
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 私有构造方法
     */
    private RedisDynamicDataSourceHolder() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取数据源
     *
     * @return redis操作类名
     */
    public static String get() {
        return THREAD_LOCAL.get();
    }

    /**
     * put方法
     *
     * @param name redis操作类名
     */
    public static void set(String name) {
        THREAD_LOCAL.set(name);
        if (log.isDebugEnabled()) {
            log.debug("Thread: {}, add dataSourceKey:[{}] to thread-local success", Thread.currentThread().getName(), name);
        }
    }

    /**
     * 清除redis操作类名
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
