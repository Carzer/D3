package com.github.d3.provider;

import com.github.d3.constants.ProfileConstants;
import feign.Experimental;
import feign.RequestTemplate;
import feign.Target;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 动态代理服务信息
 *
 * @author Carzer1020@163.com
 * @since 2021-07-08
 */
@Component
@EnableConfigurationProperties(DynamicServiceFeignProvider.DynamicServiceConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Profile(ProfileConstants.ENV_DEV)
public class DynamicServiceFeignProvider implements FeignProvider {

    /**
     * 包含key
     */
    private static final String INCLUDES_KEY = "includes";

    /**
     * 忽略key
     */
    private static final String EXCLUDES_KEY = "excludes";

    /**
     * 动态代理配置
     */
    private final DynamicServiceConfig config;

    /**
     * 是否开启debug
     */
    private final boolean isDebugEnabled = log.isDebugEnabled();

    /**
     * 动态服务信息
     */
    private Map<String, DynamicServiceConfig.DynamicServiceInfo> dynamicServices;

    /**
     * 是否有配置信息
     * 启动的时候默认为true，请求时再根据实际情况判断
     */
    private boolean configured = true;

    /**
     * 处理请求方法
     *
     * @param template 请求模板
     */
    @Override
    @Experimental
    public void apply(RequestTemplate template) {
        if (isDebugEnabled) {
            log.debug("是否开启动态服务处理：{}", configured);
        }
        if (configured) {
            Target<?> target = template.feignTarget();
            String name = target.name();
            DynamicServiceConfig.DynamicServiceInfo serviceInfo = getServiceInfo(name);
            if (serviceInfo != null && needChange(serviceInfo, template.path())) {
                String url = target.url();
                String proxy = serviceInfo.getProxy();
                Assert.state(StringUtils.hasText(proxy), "代理地址配置异常");
                if (isDebugEnabled) {
                    log.debug("开始处理服务：{}，替换目标：{}", name, proxy);
                }
                url = url.replace(name, proxy);
                template.target(url);
                // feignTarget预计会在后续版本产生作用，目前未发现明显的作用域，since 2021/07/12
                Target.HardCodedTarget<?> hardCodedTarget =
                        new Target.HardCodedTarget<>(target.type(), proxy, url);
                template.feignTarget(hardCodedTarget);
            }
        }
        if (isDebugEnabled && configured) {
            log.debug("动态服务处理完成");
        }
    }

    /**
     * 获取服务配置信息
     *
     * @param serviceName 服务名
     * @return 服务配置信息
     */
    private DynamicServiceConfig.DynamicServiceInfo getServiceInfo(String serviceName) {
        if (CollectionUtils.isEmpty(dynamicServices)) {
            dynamicServices = config.getDynamicServices();
            if (CollectionUtils.isEmpty(dynamicServices)) {
                configured = false;
                return null;
            }
        }
        return dynamicServices.get(serviceName);
    }

    /**
     * 是否需要变更服务信息
     *
     * @param serviceInfo 服务信息
     * @param uri         uri
     * @return 是否需要变更服务信息
     */
    private boolean needChange(DynamicServiceConfig.DynamicServiceInfo serviceInfo, String uri) {
        // 没有配置到url级别的，全部处理
        Map<String, List<String>> urls = serviceInfo.getUrl();
        if (CollectionUtils.isEmpty(urls)) {
            if (isDebugEnabled) {
                log.debug("应用全局替换策略");
            }
            return true;
        }
        /*
        包含的url，处理(默认全部不处理)
        包含的url优先级会高于排除的url
         */
        List<String> includes = urls.get(INCLUDES_KEY);
        if (CollectionUtils.isEmpty(includes)) {
            // 排除的url不处理(默认全部处理)
            List<String> excludes = urls.get(EXCLUDES_KEY);
            if (isDebugEnabled) {
                log.debug("应用全局替换策略({})，判断是否当前uri是否需要处理", EXCLUDES_KEY);
            }
            return CollectionUtils.isEmpty(excludes) || !excludes.contains(uri);
        } else {
            if (isDebugEnabled) {
                log.debug("非全局替换策略({})，判断是否当前uri是否需要处理", INCLUDES_KEY);
            }
            return includes.contains(uri);
        }
    }

    /**
     * 动态代理配置
     */
    @Setter
    @Getter
    @ConfigurationProperties(prefix = "dms.feign", ignoreInvalidFields = true)
    public static class DynamicServiceConfig {

        /**
         * 服务信息
         */
        private Map<String, DynamicServiceInfo> dynamicServices;

        /**
         * 服务信息
         */
        @Setter
        @Getter
        public static class DynamicServiceInfo {

            /**
             * 代理地址
             */
            private String proxy;

            /**
             * 请求url信息
             */
            private Map<String, List<String>> url;
        }
    }

}
