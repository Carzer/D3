package com.github.d3.data.jdbc;

import com.baomidou.mybatisplus.core.toolkit.AES;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;


/**
 * 自定义数据源
 *
 * @author Carzer1020@163.com
 * @since 2021-04-28
 */
@Slf4j
public final class JdbcDataSource extends HikariDataSource {

    /**
     * AES密钥
     */
    private String key = null;

    /**
     * 获取jdbc连接
     *
     * @return jdbc连接
     */
    @Override
    public String getJdbcUrl() {
        return decrypt(super.getJdbcUrl());
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return decrypt(super.getUsername());
    }

    /**
     * 获取密码
     *
     * @return 密码
     */
    @Override
    public String getPassword() {
        return decrypt(super.getPassword());
    }

    /**
     * 解密信息
     *
     * @param encryptStr 加密信息
     * @return 解密信息
     */
    private String decrypt(String encryptStr) {
        if (StringUtils.hasText(getKey())) {
            try {
                return AES.decrypt(encryptStr, this.key);
            } catch (Exception e) {
                log.warn("解密信息异常：", e);
            }
        }
        return encryptStr;
    }

    /**
     * 获取系统参数
     *
     * @return 加密key
     */
    private String getKey() {
        if (!StringUtils.hasText(this.key)) {
            this.key = System.getProperty("mpw.key");
            if (!StringUtils.hasText(this.key)) {
                this.key = System.getenv("mpw.key");
            }
        }
        return this.key;
    }
}
