package com.github.d3.service;

import com.github.d3.entity.BaseEntity;
import com.github.d3.page.PageQuery;
import com.github.d3.page.PageResult;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础服务
 *
 * @author Carzer1020@163.com
 * @since 2020-03-26
 */
@SuppressWarnings("all")
public interface BaseService<T extends BaseEntity> {

    /**
     * 插入方法
     *
     * @param entity 插入方法
     * @return 执行结果
     */
    int insert(T entity);

    /**
     * 根据条件查询一条记录
     *
     * @param entity entity
     * @return 执行结果
     */
    T findOne(T entity);

    /**
     * 获取分页信息
     *
     * @param pageQuery 分页信息
     * @param entity    查询条件
     * @return 分页结果
     */
    PageResult<?> getPage(PageQuery pageQuery, T entity);

    /**
     * 根据条件更新一条记录
     *
     * @param entity entity
     * @return 执行结果
     */
    int update(T entity);

    /**
     * 删除方法
     *
     * @param keyList 删除key集合
     * @return 执行结果
     */
    default int delete(Collection<? extends Serializable> keyList) {
        return 0;
    }

}
