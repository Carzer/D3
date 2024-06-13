package com.github.d3.util.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * FastJson操作工具类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class JacksonUtil {

    /**
     * json mapper
     */
    public static final JsonMapper MAPPER = new JsonMapper();

    /*
     * 只对bean类型生效，map与list没有效果
     */
    static {
        // unknown字段不处理
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 空字段不进行序列化
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 时间类型
        MAPPER.registerModule(new JavaTimeModule());
    }

}
