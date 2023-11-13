package com.github.d3.auth.service;

import com.github.d3.auth.entity.UserEntity;
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
     * 根据关键词获取用户
     *
     * @param key key
     * @return 用户信息
     */
    UserEntity loadUser(String key);

    /**
     * 根据手机号获取用户
     *
     * @param id userId
     * @return 用户主键
     */
    UserEntity loadUserById(Long id);

    /**
     * 更新用户描述
     *
     * @param userEntity user
     * @return int
     */
    int updateProfile(UserEntity userEntity);

    /**
     * 根据登录名查询用户名
     *
     * @param loginNames 登录名列表
     * @return 用户列表
     */
    List<UserEntity> queryByLoginName(List<String> loginNames);

}
