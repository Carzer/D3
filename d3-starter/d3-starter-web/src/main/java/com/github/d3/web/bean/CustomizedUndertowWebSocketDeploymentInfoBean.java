package com.github.d3.web.bean;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * 统一过滤器
 *
 * @author Carzer1020@163.com
 * @since 2023-01-19
 */
@Component
public class CustomizedUndertowWebSocketDeploymentInfoBean implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    /**
     * 定制化操作
     *
     * @param undertowServletWebServerFactory undertowServletWebServerFactory
     */
    @Override
    public void customize(UndertowServletWebServerFactory undertowServletWebServerFactory) {
        undertowServletWebServerFactory.addDeploymentInfoCustomizers(deploymentInfo -> {
            WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
            webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
            deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
        });
    }
}