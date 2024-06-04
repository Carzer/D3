package com.github.d3.base.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密类
 *
 * @author Carzer1020@163.com
 * @since 2019-07-16
 */
@Slf4j
public final class AuthPasswordEncoder extends BCryptPasswordEncoder {

    /**
     * 构造方法
     */
    public AuthPasswordEncoder() {
        super();
    }

    /**
     * 加密方法
     *
     * @param rawPassword 原始密码
     * @return java.lang.String
     */
    @Override
    public String encode(CharSequence rawPassword) {
        if (log.isDebugEnabled()) {
            log.debug("before encode {}", rawPassword);
        }
        return super.encode(rawPassword);
    }

    /**
     * 是否匹配
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 加密后的密码
     * @return boolean
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (log.isDebugEnabled()) {
            log.debug("rawPassword is {}", rawPassword);
            log.debug("encodedPassword is {}", encodedPassword);
        }
        return super.matches(rawPassword, encodedPassword);
    }
}
