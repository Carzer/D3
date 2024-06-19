package com.github.d3.base.auth.service;

import com.github.d3.base.entity.user.UserEntity;
import com.github.d3.base.enums.CredentialsTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * 用户信息服务实现类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
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
        // 用户已锁定
        if (user.getLocked()) {
            String accountLockedMsg = String.format("账号[%s]已锁定，请联系管理员。", username);
            log.warn(accountLockedMsg);
            throw new LockedException(accountLockedMsg);
        }
        LocalDateTime expireAt = user.getExpireAt();
        // 用户已过期
        if (expireAt != null && LocalDateTime.now().isAfter(expireAt)) {
            String accountExpiredMsg = String.format("账号[%s]已于%s过期。", username, expireAt);
            log.warn(accountExpiredMsg);
            throw new AccountExpiredException(accountExpiredMsg);
        }
        return new User(user.getName(), user.getCredentials(), Collections.emptySet());
    }
}
