package com.github.d3.springdoc.config;

import com.github.d3.springdoc.property.SpringDocAutoConfigurationProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Spring Doc 配置信息
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(SpringDocAutoConfigurationProperties.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ConditionalOnProperty(prefix = "springdoc.api-docs", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SpringDocAutoConfiguration {

    /**
     * Spring Doc相关配置
     */
    private final SpringDocAutoConfigurationProperties properties;

    /**
     * open api
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI openApi() {
        Components components = new Components();
        //添加右上角的统一安全认证
        components.addSecuritySchemes("JWT",
                new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .name("JWT")
                        .in(SecurityScheme.In.HEADER)
        );
        return new OpenAPI()
                .components(components)
                .security(List.of(new SecurityRequirement().addList("JWT")))
                .info(new Info()
                        .title(properties.getTitle())
                        .description(properties.getDescription())
                        .version(properties.getVersion())
                        .contact(new Contact().email("Carzer1020@163.com"))
                );
    }

    /**
     * 全局追加请求头
     *
     * @return 自定义方法处理
     */
    @Bean
    public OperationCustomizer customize() {
        return (operation, handlerMethod) -> operation.addParametersItem(
                new HeaderParameter().required(false).name("Source")
        );
    }

}
