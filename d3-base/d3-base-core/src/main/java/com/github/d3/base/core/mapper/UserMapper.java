package com.github.d3.base.core.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.d3.base.core.entity.user.UserEntity;
import com.github.d3.base.enums.CredentialsTypeEnum;
import com.github.d3.data.jdbc.mapper.MpBasisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 用户信息mapper
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
@Mapper
public interface UserMapper extends MpBasisMapper<UserEntity> {

    /**
     * 根据账号信息查询用户(查询结果附带凭证)
     *
     * @param account 账号
     * @return 用户
     */
    UserEntity loadUserWithCredentials(@Param("account") String account, @Param("credentialsType") CredentialsTypeEnum credentialsType);

    /**
     * 分页方法，关联'租户-用户信息表'
     *
     * @param page     page
     * @param queryMap 分页信息
     * @return 执行结果
     */
    IPage<UserEntity> getUserPage(@Param("page") Page<UserEntity> page, @Param("queryMap") Map<String, Object> queryMap);

}
