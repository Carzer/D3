package com.github.d3.auth.mapper;

import com.github.d3.auth.entity.user.UserAccountEntity;
import com.github.d3.data.jdbc.mapper.MpBasisMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户账号Mapper
 *
 * @author Carzer1020@163.com
 * @since 2024-03-28
 */
@Mapper
public interface UserAccountMapper extends MpBasisMapper<UserAccountEntity> {

}
