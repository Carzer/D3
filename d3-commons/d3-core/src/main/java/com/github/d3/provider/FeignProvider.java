package com.github.d3.provider;

import feign.RequestTemplate;

/**
 * Feign Provider
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public interface FeignProvider {

    /**
     * 处理请求方法
     *
     * @param requestTemplate 请求模板
     */
    void apply(RequestTemplate requestTemplate);
}
