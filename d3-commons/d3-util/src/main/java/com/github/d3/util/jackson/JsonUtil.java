package com.github.d3.util.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Json工具类
 *
 * @author Carzer1020@163.com
 * @since 2021-07-06
 */
@Slf4j
@UtilityClass
public class JsonUtil extends JacksonUtil {

    /**
     * 转换为json
     *
     * @param value value
     * @return json串
     */
    public String toJson(Object value) {
        try {
            return ObjectUtils.isEmpty(value) ? null : MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("转换为Json异常：", e);
            return null;
        }
    }

    /**
     * 转换为json
     *
     * @param json json串
     * @return 转换结果
     */
    public <T> T fromJson(String json, Class<T> valueType) {
        try {
            return ObjectUtils.isEmpty(json) ? null : MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.error("由Json转换异常：", e);
            return null;
        }
    }

    /**
     * 读取json树
     *
     * @param json json串
     * @return json树
     */
    public JsonNode readTree(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("读取Json对象为树，发生异常：", e);
            return MAPPER.createObjectNode();
        }
    }

    /**
     * 压缩json内容
     * <p>
     * {@link Deflater}
     *
     * @param json 压缩内容
     * @return 压缩结果
     */
    public String compress(String json) {
        Deflater deflater = new Deflater(Deflater.BEST_SPEED);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256);) {
            deflater.setInput(json.getBytes(StandardCharsets.UTF_8));
            deflater.finish();
            final byte[] bytes = new byte[256];
            while (!deflater.finished()) {
                int length = deflater.deflate(bytes);
                outputStream.write(bytes, 0, length);
            }
            return Base64Utils.encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            log.error("压缩json内容异常：", e);
            return null;
        } finally {
            deflater.end();
        }
    }

    /**
     * 解压json内容
     *
     * @param compressedJson 压缩过的json
     * @return json
     */
    public String decompress(String compressedJson) {
        Inflater inflater = new Inflater();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256);) {
            byte[] decode = Base64Utils.decodeFromString(compressedJson);
            inflater.setInput(decode);
            final byte[] bytes = new byte[256];
            while (!inflater.finished()) {
                int length = inflater.inflate(bytes);
                outputStream.write(bytes, 0, length);
            }
            return outputStream.toString();
        } catch (Exception e) {
            log.error("解压json内容异常：", e);
            return null;
        } finally {
            inflater.end();
        }
    }
}
