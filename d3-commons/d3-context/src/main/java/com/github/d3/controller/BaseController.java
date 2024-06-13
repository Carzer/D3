package com.github.d3.controller;


import com.github.d3.R;
import com.github.d3.dto.BaseDTO;
import com.github.d3.page.PageQuery;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

import static com.github.d3.constants.UrlConstants.*;


/**
 * controller父类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@SuppressWarnings("all")
@PreAuthorize("isAuthenticated()")
@Component
public abstract class BaseController<T extends BaseDTO> {

    /**
     * 查询方法
     *
     * @param dto 查询信息
     * @return 查询结果
     */
    @PreAuthorize("hasAuth('{}" + GET + "')")
    public abstract R<?> get(T dto, Principal principal);

    /**
     * 分页方法
     *
     * @param dto 查询信息
     * @return 分页信息
     */
    @PreAuthorize("hasAuth('{}" + GET + "')")
//    @PreAuthorize("denyAll()")
    public abstract R<?> getPage(PageQuery pageQuery, T dto, Principal principal);

    /**
     * 插入方法
     *
     * @param dto 插入信息
     * @return 执行结果
     */
    @PreAuthorize("hasAuth('{}" + INSERT + "')")
    public abstract R<?> insert(@Valid @RequestBody T dto, Principal principal);

    /**
     * 更新方法
     *
     * @param dto 更新信息
     * @return 执行结果
     */
    @PreAuthorize("hasAuth('{}" + UPDATE + "')")
    public abstract R<?> update(@Valid @RequestBody T dto, Principal principal);

    /**
     * 删除方法
     *
     * @param keys keys
     * @return 执行结果
     */
    @PreAuthorize("hasAuth('{}" + DELETE + "')")
    public abstract R<?> delete(@RequestBody Collection<? extends Serializable> keys, Principal principal);
}
