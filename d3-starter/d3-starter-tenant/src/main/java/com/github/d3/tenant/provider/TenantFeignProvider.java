package com.github.d3.tenant.provider;

import com.github.d3.provider.FeignProvider;
import com.github.d3.tenant.TenantContext;
import com.github.d3.tenant.TenantInfo;
import com.github.d3.tenant.constants.TenantConstants;
import com.github.d3.util.jackson.JsonUtil;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * Feign请求对租户功能的支持
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Component
public class TenantFeignProvider implements FeignProvider {

    /**
     * 处理请求方法
     *
     * @param requestTemplate 请求模板
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        TenantInfo info = TenantContext.TenantInfoHolder.getInfo();
        if (info != null) {
            requestTemplate.header(TenantConstants.HEADER_TENANT_INFO, JsonUtil.toJson(info));
        }
    }
}
