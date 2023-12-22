package com.github.d3.auth.service.impl;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.d3.auth.entity.user.UserEntity;
import com.github.d3.auth.enums.CredentialsTypeEnum;
import com.github.d3.auth.mapper.UserMapper;
import com.github.d3.auth.service.UserService;
import com.github.d3.auth.util.AuthPasswordEncoder;
import com.github.d3.data.jdbc.service.impl.MpBaseServiceImpl;
import com.github.d3.page.PageQuery;
import com.github.d3.page.PageResult;
import com.github.d3.util.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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
        // TODO: 2023/11/14 禁止删除管理员用户
        return userMapper.deleteById(id);
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
        Page<UserEntity> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        Map<String, Object> queryMap = new HashMap<>(MapUtil.HASHMAP_DEFAULT_INITIAL_CAPACITY);
        queryMap.put("query", pageQuery);
        // 拼接查询条件：名称、编码
        if (StringUtils.hasText(userEntity.getName())
                || StringUtils.hasText(userEntity.getCode())) {
            StringBuilder stringBuilder = new StringBuilder();
            Optional.ofNullable(userEntity.getName()).ifPresent(stringBuilder::append);
            Optional.ofNullable(userEntity.getCode()).ifPresent(stringBuilder::append);
            queryMap.put("queryStr", stringBuilder.toString());
        }
        IPage<UserEntity> userPage = userMapper.getUserPage(page, queryMap);
        return pageResult(userPage);
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
//        String rawPass = userEntity.getPassword();
//        String encodedPass = StringUtils.hasText(rawPass) ? passwordEncoder.encode(rawPass) : "$2a$10$89UJRZ6A.ubPmT9MrN6iEePGKdmW2N2b8tIe3Ng1MAVaTfRB2gTKC";
//        userEntity.setPassword(encodedPass);
    }

    /**
     * 检查用户信息是否唯一
     *
     * @param userEntity 用户信息
     */
    private void checkUnique(UserEntity userEntity) {
        // todo 检查登录名是否重复

    }

    /**
     * 根据用户名查询用户
     *
     * @param username the username identifying the user whose data is required.
     * @return 用户
     * @throws UsernameNotFoundException 未查询到用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userMapper.loadUserWithCredentials(username, CredentialsTypeEnum.PASSWORD);
        if (user == null) {
            throw new UsernameNotFoundException("未查询到用户");
        }
        return new User(user.getName(), user.getCredentials(), Collections.emptySet());
    }
}
