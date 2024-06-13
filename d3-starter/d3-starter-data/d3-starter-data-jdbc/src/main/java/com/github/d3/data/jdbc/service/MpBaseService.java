package com.github.d3.data.jdbc.service;

import com.github.d3.data.jdbc.entity.MpBaseEntity;
import com.github.d3.service.BaseService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基础服务
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public interface MpBaseService<T extends MpBaseEntity> extends BaseService<T> {

    /**
     * 删除方法
     *
     * @param id id
     * @return 执行结果
     */
    int logicDeleteById(Long id);

    /**
     * 根据编码批量删除
     *
     * @param identifiers 标识符
     * @return 执行结果
     */
    int logicDeleteBatchByIds(List<Long> identifiers);

    /**
     * 根据ID物理删除
     *
     * @param id ID
     * @return 执行结果
     */
    int physicalDeleteById(Serializable id);

    /**
     * 根据ID批量物理删除
     *
     * @param idList ID集合
     * @return 执行结果
     */
    int physicalDeleteBatchByIds(Collection<? extends Serializable> idList);

    /**
     * 根据列名物理删除
     *
     * @param column 列名
     * @param item   值
     * @return 执行结果
     */
    int physicalDelete(String column, Serializable item);

    /**
     * 根据列名批量物理删除
     *
     * @param column 列名
     * @param list   集合
     * @return 执行结果
     */
    int physicalDeleteBatch(String column, Collection<? extends Serializable> list);
}
