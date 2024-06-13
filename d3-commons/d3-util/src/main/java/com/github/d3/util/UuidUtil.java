package com.github.d3.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@UtilityClass
public class UuidUtil {

    /**
     * 生成36位uuid
     *
     * @return uuid
     */
    public String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成32位uuid
     *
     * @return uuid
     */
    public String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll(".-", "");
    }

}
