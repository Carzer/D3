package com.github.d3.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.github.d3.auth.entity.UserEntity;
import com.github.d3.auth.mapper.UserMapper;
import com.github.d3.auth.service.UserService;
import com.github.d3.auth.util.AuthPasswordEncoder;
import com.github.d3.code.RCode;
import com.github.d3.data.jdbc.service.impl.MpBaseServiceImpl;
import com.github.d3.exception.BizException;
import com.github.d3.page.PageQuery;
import com.github.d3.page.PageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * 用户信息相关服务
 *
 * @author Carzer1020@163.com
 * @since 2022-11-28
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl extends MpBaseServiceImpl<UserMapper, UserEntity> implements UserService, UserDetailsService {

    /**
     * 用户mapper
     */
    private final UserMapper userMapper;

    /**
     * ID生成器
     */
    private final DefaultIdentifierGenerator identifierGenerator;

    /**
     * 用户服务
     * Service中注入自身，是为了方便获取代理对象，防止注解失效问题
     */
    private UserService userService;

    /**
     * 设置用户服务
     *
     * @param userService 用户服务
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据关键词获取用户
     * 当前支持手机号及用户名
     *
     * @param key key
     * @return 用户信息
     */
    @Override
    public UserEntity loadUser(String key) {
        // TODO: 2023/11/13 tablename:userAccount,columns:phone-id,mail-id,name-id
        return null;
    }

    /**
     * 根据手机号获取用户
     *
     * @param id userId
     * @return 用户主键
     */
    @Override
    public UserEntity loadUserById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 更新用户信息
     *
     * @param userEntity user
     * @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UserEntity userEntity) {
        return userMapper.updateById(userEntity);
    }

    /**
     * 更新用户描述
     *
     * @param userEntity user
     * @return int
     */
    @Override
    public int updateProfile(UserEntity userEntity) {
        return userMapper.updateById(userEntity);
    }

    /**
     * 根据登录名查询用户名
     *
     * @param loginNames 登录名列表
     * @return 用户列表
     */
    @Override
    public List<UserEntity> queryByLoginName(List<String> loginNames) {
        return lambdaQuery().in(UserEntity::getLoginName, loginNames).list();
    }

    /**
     * 插入用户信息
     * <p>
     *
     * @param userEntity 用户
     * @return 用户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(UserEntity userEntity) {
        // 检查及完善用户信息
        checkUnique(userEntity);
        completeInfo(userEntity);
        return userMapper.insert(userEntity);
    }

    /**
     * 删除用户信息
     *
     * @param id id
     * @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int logicDeleteById(Long id) {
        lambdaQuery().eq(UserEntity::getId, id)
                .select(UserEntity::getLoginName)
                .oneOpt()
                .ifPresentOrElse(
                        user -> {
                            // admin用户不准删除
                            Assert.isTrue(!"admin".equalsIgnoreCase(user.getLoginName()), "admin用户不可删除!");
                            // 清除用户与其他信息的关系
                            userMapper.deleteById(id);
                        }
                        , () -> {
                            throw new BizException(RCode.USER_NOT_EXISTED);
                        });
        return 1;
    }

    /**
     * 获取用户信息页
     *
     * @param pageQuery  查询条件
     * @param userEntity 查询条件
     * @return 执行结果
     */
    @Override
    public PageResult<UserEntity> getPage(PageQuery pageQuery, UserEntity userEntity) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeRight(UserEntity::getLoginName, userEntity.getLoginName());
        return pageResult(pageQuery, wrapper);
    }

    /**
     * 批量删除用户
     *
     * @param ids 编码
     * @return 执行结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int logicDeleteBatchByIds(List<Long> ids) {
        ids.forEach(this::logicDeleteById);
        return ids.size();
    }

    /**
     * 完善用户信息
     *
     * @param userEntity 用户信息
     */
    private void completeInfo(UserEntity userEntity) {
        // 用户信息补充
        if (userEntity.getId() == null) {
            userEntity.setId(identifierGenerator.nextId(userEntity));
        }

        /*由于PasswordEncoder与UserService同在SecurityConfig中实例化
         * {@link SecurityConfig#passwordEncoder}
         * {@link SecurityConfig#userService}
         * {@link SecurityConfig#userDetailsService}
         * 所以Spring无法在初始化当前类时注入PasswordEncoder
         * 故使用新建对象的方式来使用{@link AuthPasswordEncoder}
         */
        AuthPasswordEncoder passwordEncoder = new AuthPasswordEncoder();
        // 密码加密
        String rawPass = userEntity.getPassword();
        String encodedPass = StringUtils.hasText(rawPass) ? passwordEncoder.encode(rawPass) : "$2a$10$89UJRZ6A.ubPmT9MrN6iEePGKdmW2N2b8tIe3Ng1MAVaTfRB2gTKC";
        userEntity.setPassword(encodedPass);
    }

    /**
     * 检查用户信息是否唯一
     *
     * @param userEntity 用户信息
     */
    private void checkUnique(UserEntity userEntity) {
        // 检查登录名是否重复
        if (lambdaQuery().eq(UserEntity::getLoginName, userEntity.getLoginName())
                .count() > 0
        ) {
            throw new BizException(RCode.LOGIN_NAME_EXISTED);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = loadUser(username);
        return new User(user.getName(), user.getPassword(), Collections.emptySet());
    }
}
