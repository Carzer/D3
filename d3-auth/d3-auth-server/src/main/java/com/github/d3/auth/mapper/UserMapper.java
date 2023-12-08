package com.github.d3.auth.mapper;

import com.github.d3.auth.entity.user.UserEntity;
import com.github.d3.data.jdbc.mapper.MpBasisMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息mapper
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
public interface UserMapper extends MpBasisMapper<UserEntity> {

    /**
     * 根据账号信息查询用户(查询结果附带凭证)
     *
     * @param account 账号
     * @return 用户
     */
    UserEntity loadUserWithCredentials(@Param("account") String account);
}
