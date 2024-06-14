package com.github.d3.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.d3.base.code.AuthCode;
import com.github.d3.base.entity.user.UserCredentialsEntity;
import com.github.d3.base.entity.user.UserEntity;
import com.github.d3.base.enums.CredentialsTypeEnum;
import com.github.d3.base.enums.UserTypeEnum;
import com.github.d3.base.mapper.UserMapper;
import com.github.d3.base.service.UserAccountService;
import com.github.d3.base.service.UserService;
import com.github.d3.base.util.AuthPasswordEncoder;
import com.github.d3.data.jdbc.service.impl.MpBaseServiceImpl;
import com.github.d3.exception.BizException;
import com.github.d3.page.PageQuery;
import com.github.d3.page.PageResult;
import com.github.d3.util.MapUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户信息相关服务
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl extends MpBaseServiceImpl<UserMapper, UserEntity> implements UserService {

    /**
     * 用户账号服务
     */
    private final UserAccountService userAccountService;

    /**
     * 用户mapper
     */
    private final UserMapper userMapper;

    /**
     * ID生成器
     */
    private final DefaultIdentifierGenerator identifierGenerator = DefaultIdentifierGenerator.getInstance();

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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(UserEntity userEntity) {
        return saveWithCredentials(userEntity, null);
    }

    /**
     * 保存用户信息的时候，同时保存凭证
     *
     * @param user        用户信息
     * @param credentials 凭证信息
     * @return 保存结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveWithCredentials(UserEntity user, List<UserCredentialsEntity> credentials) {
        // 检查及完善用户信息
        checkAccount(user);
        // 补充相关信息
        completeUserInfo(user);
        completeCredentials(credentials);
        return userMapper.insert(user);
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
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 禁止删除管理员及更高级别用户
        lambdaQueryWrapper.eq(UserEntity::getId, id).gt(UserEntity::getUserType, UserTypeEnum.ADMIN);
        return userMapper.delete(lambdaQueryWrapper);
    }

    /**
     * 根据账号查询用户，并携带凭证信息
     *
     * @param account         账号
     * @param credentialsType 凭证类型
     * @return 用户
     */
    @Override
    public UserEntity loadUserWithCredentials(String account, CredentialsTypeEnum credentialsType) {
        return userMapper.loadUserWithCredentials(account, credentialsType);
    }

    /**
     * 获取用户信息页
     * <p>
     * <p>
     * 当前方法使用了mysql的全文索引，所以语句也进行了相应的调整"match() against()"，如果查询语句用在非全文索引字段，将直接报错
     * <p>
     * 示例：
     * <p>
     * 创建索引语句:<code>create fulltext index user_fulltext_index on user(name,code)  with parser ngram;</code>
     * <p>
     * 查询条件语句:<code>match(usr.name,usr.code) against (#{queryMap.queryStr} in boolean mode)</code>
     *
     * @param pageQuery  查询条件
     * @param userEntity 查询条件
     * @return 执行结果
     */
    @Override
    public PageResult<UserEntity> getPage(PageQuery pageQuery, @NonNull UserEntity userEntity) {
        Page<UserEntity> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        Map<String, Object> queryMap = MapUtil.hashMap();
        queryMap.put("query", pageQuery);
        // 拼接查询条件：名称、唯一标识
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.hasText(userEntity.getName())) {
            stringBuilder.append("+>").append(userEntity.getName());
        }
        if (StringUtils.hasText(userEntity.getUid())) {
            stringBuilder.append(" +").append(userEntity.getUid());

        }
        if (!stringBuilder.isEmpty()) {
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
    private void completeUserInfo(UserEntity userEntity) {
        // 用户信息补充
        if (userEntity.getId() == null) {
            userEntity.setId(identifierGenerator.nextId(userEntity));
        }
    }

    /**
     * 完善凭证信息
     *
     * @param credentials 凭证信息
     */
    private void completeCredentials(List<UserCredentialsEntity> credentials) {
        if (!CollectionUtils.isEmpty(credentials)) {
            credentials.forEach(c -> {
                if (c.getCredentialsType().equals(CredentialsTypeEnum.PASSWORD)) {
                    AuthPasswordEncoder passwordEncoder = new AuthPasswordEncoder();
                    // 密码加密
                    String rawPass = c.getCredentials();
                    String encodedPass = StringUtils.hasText(rawPass) ? passwordEncoder.encode(rawPass) : "$2a$10$89UJRZ6A.ubPmT9MrN6iEePGKdmW2N2b8tIe3Ng1MAVaTfRB2gTKC";
                    c.setCredentials(encodedPass);
                }
            });
        }
    }

    /**
     * 检查用户信息
     *
     * @param userEntity 用户信息
     */
    private void checkAccount(UserEntity userEntity) {
        Set<String> accounts = Set.of(userEntity.getUid(), userEntity.getPhone(), userEntity.getEmail());
        if (userAccountService.exists(accounts)) {
            throw new BizException(AuthCode.LOGIN_ACCOUNT_EXISTED);
        }
    }

}
