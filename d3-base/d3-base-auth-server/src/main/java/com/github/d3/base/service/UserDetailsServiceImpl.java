package com.github.d3.base.service;

import com.github.d3.base.entity.user.UserEntity;
import com.github.d3.base.enums.CredentialsTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 用户信息服务实现类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 用户服务信息
     */
    private final UserService userService;

    /**
     * 根据用户名查询用户
     *
     * @param username the username identifying the user whose data is required.
     * @return 用户
     * @throws UsernameNotFoundException 未查询到用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.loadUserWithCredentials(username, CredentialsTypeEnum.PASSWORD);
        if (user == null) {
            log.info("根据账号[{}]未查询到用户", username);
            throw new UsernameNotFoundException("未查询到用户");
        }
        return new User(user.getName(), user.getCredentials(), Collections.emptySet());
    }
}
