package com.github.d3.util;


import cn.hutool.core.util.NumberUtil;
import com.github.d3.constants.ProfileConstants;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.util.Optional;

/**
 * 缓存持续时间工具类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@UtilityClass
public class DurationUtil {

    /**
     * 当前激活的环境
     */
    private String activeProfile = null;

    /**
     * 10分钟
     *
     * @return 10分钟
     */
    public Duration tenMinutes() {
        if (ProfileConstants.ENV_PROD.equalsIgnoreCase(getActiveProfile())) {
            return Duration.ofMinutes(10L - NumberUtil.generateRandomNumber(0, 5, 1)[0]);
        }
        return Duration.ofSeconds(10L);
    }

    /**
     * 半小时
     *
     * @return 半小时
     */
    public Duration halfHour() {
        if (ProfileConstants.ENV_PROD.equalsIgnoreCase(getActiveProfile())) {
            return Duration.ofMinutes(30L + NumberUtil.generateRandomNumber(-10, 10, 1)[0]);
        }
        return Duration.ofSeconds(30L);
    }

    /**
     * 一小时
     *
     * @return 一小时
     */
    public Duration anHour() {
        if (ProfileConstants.ENV_PROD.equalsIgnoreCase(getActiveProfile())) {
            return Duration.ofMinutes(60L + NumberUtil.generateRandomNumber(-10, 10, 1)[0]);
        }
        return Duration.ofSeconds(60L);
    }

    /**
     * 获取当前激活的环境
     *
     * @return 当前激活的环境
     */
    private String getActiveProfile() {
        if (activeProfile == null) {
            activeProfile = Optional.ofNullable(System.getProperty("spring.profiles.active")).orElse("");
        }
        return activeProfile;
    }
}
