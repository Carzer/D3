package com.github.d3.auth.service;

import com.github.d3.auth.entity.user.UserCredentialsEntity;
import com.github.d3.auth.entity.user.UserEntity;
import com.github.d3.data.jdbc.service.MpBaseService;

import java.util.List;

/**
 * 用户信息相关服务
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
public interface UserService extends MpBaseService<UserEntity> {

    /**
     * 保存用户信息的时候，同时保存凭证
     *
     * @param user        用户信息
     * @param credentials 凭证信息
     * @return 保存结果
     */
    int saveWithCredentials(UserEntity user, List<UserCredentialsEntity> credentials);
}
