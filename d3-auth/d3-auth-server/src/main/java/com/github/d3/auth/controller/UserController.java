package com.github.d3.auth.controller;

import com.github.d3.annotations.security.PreAuth;
import com.github.d3.auth.dto.UserDTO;
import com.github.d3.auth.entity.user.UserEntity;
import com.github.d3.auth.service.UserService;
import com.github.d3.data.jdbc.controller.MpBaseController;
import com.github.d3.data.jdbc.service.MpBaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "User", description = "用户信息")
@PreAuth(value = "user")
public class UserController extends MpBaseController<UserDTO, UserEntity> {

    /**
     * 用户信息服务
     */
    private final UserService userService;

    /**
     * 获取服务方法
     *
     * @return 获取服务
     */
    @Override
    protected MpBaseService<UserEntity> getBaseService() {
        return this.userService;
    }

    /**
     * 检查方法
     *
     * @param userDTO 用户信息
     */
    @Override
    protected void check(UserDTO userDTO) {
        Assert.hasText(userDTO.getName(), "用户名不能为空");
    }

}
