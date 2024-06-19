package com.github.d3.base.auth.service.impl;

import com.github.d3.base.entity.user.UserAccountEntity;
import com.github.d3.base.mapper.UserAccountMapper;
import com.github.d3.base.auth.service.UserAccountService;
import com.github.d3.data.jdbc.service.impl.MpBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 用户账号服务
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAccountServiceImpl extends MpBaseServiceImpl<UserAccountMapper, UserAccountEntity> implements UserAccountService {

    /**
     * 用户账号mapper
     */
    private final UserAccountMapper userAccountMapper;

    /**
     * 判断账号是否已经存在
     *
     * @param accountList 用户账号列表
     * @return 是否已经存在
     */
    @Override
    public boolean exists(Set<String> accountList) {
        return lambdaQuery()
                .in(UserAccountEntity::getAccount, accountList)
                .count() > 0L;
    }
}
