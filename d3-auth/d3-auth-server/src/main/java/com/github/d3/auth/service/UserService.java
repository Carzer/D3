package com.github.d3.auth.service;

import com.github.d3.auth.entity.user.UserEntity;
import com.github.d3.data.jdbc.service.MpBaseService;

/**
 * 用户信息相关服务
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
public interface UserService extends MpBaseService<UserEntity> {

    /**
     * 根据关键词获取用户
     *
     * @param account account
     * @return 用户信息
     */
    UserEntity loadUser(String account);

}
