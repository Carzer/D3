package com.github.d3.filter.trim;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.d3.filter.AbstractRequestWrapper;
import com.github.d3.util.jackson.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * 请求过滤
 *
 * @author Carzer1020@163.com
 * @since 2020-10-26
 */
@Slf4j
public final class RequestTrimWrapper extends AbstractRequestWrapper {

    /**
     * 构造方法
     *
     * @param request 请求
     */
    public RequestTrimWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出
        super(request);
        // 将参数表，赋予给当前的Map以便于持有request中的参数
        this.params.putAll(request.getParameterMap());
        this.modifyParameterValues();
    }

    /**
     * 重写getInputStream方法  body中的参数必须通过流才能获取到值
     */
    @Override
    public jakarta.servlet.ServletInputStream getInputStream() throws IOException {
        String header = super.getHeader(HttpHeaders.CONTENT_TYPE);
        // json类型才进行处理，非json类型，直接返回
        if (isJsonType(header)) {
            String json = IOUtils.toString(super.getInputStream(), StandardCharsets.UTF_8);
            if (StringUtils.hasText(json)) {
                boolean debug = log.isDebugEnabled();
                if (debug) {
                    log.debug(String.format("去除请求数据两端的空格前参数：%s", json));
                }
                // 先转为Jackson的Json对象，去除空格后转回String
                JsonNode jsonNode = JsonUtil.readTree(json);
                json = trimNode(jsonNode).toString();
                if (debug) {
                    log.debug(String.format("去除请求数据两端的空格后参数：%s", json));
                }
                ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
                return new ServletInputStream(bis);
            } else {
                return super.getInputStream();
            }
        } else {
            return super.getInputStream();
        }
    }

    /**
     * 将parameter的值去除空格后重写回去
     */
    private void modifyParameterValues() {
        params.forEach((key, value) -> {
            if (value != null && value.length > 0) {
                for (int i = 0; i < value.length; i++) {
                    value[i] = value[i].trim();
                }
                params.put(key, value);
            }
        });
    }

    /**
     * trim 方法
     *
     * @param jsonNode 传入参数
     * @return trim结果
     */
    private JsonNode trimNode(JsonNode jsonNode) {
        // 纯文本
        if (jsonNode.isTextual()) {
            String val = jsonNode.textValue();
            if (val != null) {
                jsonNode = new TextNode(val.trim());
            }
        }
        // 非空容器
        else if (jsonNode.isContainerNode() && !jsonNode.isEmpty()) {
            // Map类型
            if (jsonNode.isObject()) {
                ObjectNode objectNode = (ObjectNode) jsonNode;
                Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> next = fields.next();
                    objectNode.set(next.getKey(), trimNode(next.getValue()));
                }
            }
            // Array或者List
            else if (jsonNode.isArray()) {
                ArrayNode arrayNode = (ArrayNode) jsonNode;
                for (int i = 0; i < arrayNode.size(); i++) {
                    arrayNode.set(i, trimNode(arrayNode.get(i)));
                }
            }
        }
        return jsonNode;
    }
}