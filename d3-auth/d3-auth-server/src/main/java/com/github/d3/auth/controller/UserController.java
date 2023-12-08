package com.github.d3.auth.controller;

import com.github.d3.R;
import com.github.d3.annotations.security.PreAuth;
import com.github.d3.auth.dto.UserDTO;
import com.github.d3.auth.entity.user.UserEntity;
import com.github.d3.auth.service.UserService;
import com.github.d3.data.jdbc.controller.MpBaseController;
import com.github.d3.data.jdbc.service.MpBaseService;
import com.github.d3.page.PageQuery;
import com.github.d3.page.PageResult;
import com.github.d3.util.BeanCopyUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.github.d3.constants.UrlConstants.GET_PAGE;

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
     * 分页方法
     *
     * @param pageQuery 查询信息
     * @param userDTO   用户信息
     * @return 执行结果
     */
    @Operation(summary = "分页方法", description = "分页方法")
    @GetMapping(GET_PAGE)
    @Override
    public R<PageResult<UserDTO>> getPage(PageQuery pageQuery, UserDTO userDTO, @Parameter(hidden = true) Principal principal) {
        UserEntity userEntity = BeanCopyUtil.copy(userDTO, UserEntity.class);
        PageResult<?> page = userService.getPage(pageQuery, userEntity);
        return new R<>(BeanCopyUtil.convertPage(page, UserDTO.class));
    }

    /**
     * 检查方法
     *
     * @param userDTO 用户信息
     */
    @Override
    protected void check(UserDTO userDTO) {
        Assert.hasText(userDTO.getLoginName(), "登陆名不能为空");
        Assert.hasText(userDTO.getName(), "用户名不能为空");
    }

}
