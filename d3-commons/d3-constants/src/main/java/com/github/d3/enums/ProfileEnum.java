package com.github.d3.enums;

import com.github.d3.constants.ProfileConstants;
import lombok.Getter;

/**
 * Profile 枚举
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Getter
public enum ProfileEnum {

    /**
     * 启动环境key
     */
    ENV_DEV(ProfileConstants.ENV_DEV, "开发环境"),
    ENV_TEST(ProfileConstants.ENV_TEST, "测试环境"),
    ENV_PROD(ProfileConstants.ENV_PROD, "生产环境");

    /**
     * key
     */
    private final String key;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param key  key
     * @param desc 描述
     */
    ProfileEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
