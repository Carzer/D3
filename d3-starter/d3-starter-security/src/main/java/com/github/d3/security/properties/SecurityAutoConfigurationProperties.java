package com.github.d3.security.properties;

import cn.hutool.core.util.ArrayUtil;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置
 *
 * @author Carzer1020@163.com
 * @since 2020-11-19
 */
@Getter
@Component
@ConfigurationProperties(prefix = "dms.security", ignoreInvalidFields = true)
public class SecurityAutoConfigurationProperties {

    /**
     * 鉴权排除的url列表
     */
    private String[] excludedPaths = new String[]{"/v3/api-docs", "/common/**"};

    /**
     * 设置方法
     *
     * @param excludedPaths 排除的路径
     */
    public void setExcludedPaths(String[] excludedPaths) {
        if (ArrayUtil.isNotEmpty(excludedPaths)) {
            this.excludedPaths = ArrayUtil.addAll(this.excludedPaths, excludedPaths);
        }
    }
}
