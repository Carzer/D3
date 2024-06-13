package com.github.d3.data.jdbc.controller;


import com.github.d3.R;
import com.github.d3.controller.BaseController;
import com.github.d3.data.jdbc.dto.MpBaseDTO;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import com.github.d3.data.jdbc.service.MpBaseService;
import com.github.d3.page.PageQuery;
import com.github.d3.page.PageResult;
import com.github.d3.util.BeanCopyUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
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
@Slf4j
@Component
public abstract class MpBaseController<T extends MpBaseDTO, E extends MpBaseEntity> extends BaseController<T> {

    /**
     * dto类型
     */
    private Class<T> dtoClass;

    /**
     * entity类型
     */
    private Class<E> entityClass;

    /**
     * 获取服务方法
     *
     * @return 服务
     */
    protected abstract MpBaseService<E> getBaseService();

    /**
     * 检查方法
     *
     * @param dto dto
     */
    protected void check(T dto) {
        //此处判断提交的数据是否符合规则
    }

    /**
     * 查询方法
     *
     * @param dto 查询信息
     * @return 查询结果
     */
    @Operation(summary = "查询方法", description = "查询方法")
    @Parameter(name = "dto", description = "查询条件")
    @GetMapping(GET)
    public R<?> get(T dto, @Parameter(hidden = true) Principal principal) {
        E entity = BeanCopyUtil.copy(dto, getEntityClass());
        T result = BeanCopyUtil.copy(getBaseService().findOne(entity), dto);
        return new R<>(result);
    }

    /**
     * 分页方法
     *
     * @param dto 查询信息
     * @return 分页信息
     */
    @Operation(summary = "分页方法", description = "分页方法")
    @Parameter(name = "pageQuery", description = "分页信息")
    @Parameter(name = "dto", description = "查询条件")
    @GetMapping(GET_PAGE)
    public R<?> getPage(PageQuery pageQuery, T dto, @Parameter(hidden = true) Principal principal) {
        PageResult<?> page = getBaseService().getPage(pageQuery, BeanCopyUtil.copy(dto, getEntityClass()));
        return new R<>(BeanCopyUtil.convertPage(page, getDtoClass()));
    }

    /**
     * 插入方法
     *
     * @param dto 插入信息
     * @return 执行结果
     */
    @Operation(summary = "插入方法", description = "插入方法")
    @Parameter(name = "dto", description = "插入信息")
    @PostMapping(INSERT)
    public R<?> insert(@Valid @RequestBody T dto, @Parameter(hidden = true) Principal principal) {
        check(dto);
        E entity = BeanCopyUtil.copy(dto, getEntityClass());
        getBaseService().insert(entity);
        return R.success();
    }

    /**
     * 更新方法
     *
     * @param dto 更新信息
     * @return 执行结果
     */
    @Operation(summary = "更新方法", description = "更新方法")
    @Parameter(name = "dto", description = "更新信息")
    @PutMapping(UPDATE)
    public R<?> update(@Valid @RequestBody T dto, @Parameter(hidden = true) Principal principal) {
        check(dto);
        E entity = BeanCopyUtil.copy(dto, getEntityClass());
        getBaseService().update(entity);
        return R.success();
    }

    /**
     * 删除方法
     *
     * @param keys keys
     * @return 执行结果
     */
    @Operation(summary = "删除方法", description = "删除方法")
    @Parameter(name = "ids", description = "ids")
    @DeleteMapping(DELETE)
    public R<?> delete(@RequestBody Collection<? extends Serializable> keys, @Parameter(hidden = true) Principal principal) {
        getBaseService().delete(keys);
        return R.success();
    }

    /**
     * 获取dto范型实际类型
     *
     * @return 类型
     */
    private Class<T> getDtoClass() {
        if (this.dtoClass == null) {
            this.dtoClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return this.dtoClass;
    }

    /**
     * 获取entity范型实际类型
     *
     * @return 类型
     */
    private Class<E> getEntityClass() {
        if (this.entityClass == null) {
            this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return this.entityClass;
    }

}
