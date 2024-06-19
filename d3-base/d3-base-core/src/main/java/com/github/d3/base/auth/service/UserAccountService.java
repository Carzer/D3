package com.github.d3.base.auth.service;

import com.github.d3.base.entity.user.UserAccountEntity;
import com.github.d3.data.jdbc.service.MpBaseService;

import java.util.Set;

/**
 * 用户账号服务
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public interface UserAccountService extends MpBaseService<UserAccountEntity> {

    /**
     * 判断账号是否存在
     *
     * @param accountList 用户账号列表
     * @return 是否存在
     */
    boolean exists(Set<String> accountList);
}
