package com.github.d3.data.jdbc.service.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import com.github.d3.data.jdbc.mapper.MpBasisMapper;
import com.github.d3.data.jdbc.service.MpBaseService;
import com.github.d3.page.PageQuery;
import com.github.d3.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基础服务实现类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public class MpBaseServiceImpl<M extends MpBasisMapper<T>, T extends MpBaseEntity> extends ServiceImpl<M, T> implements MpBaseService<T> {

    /**
     * 默认逻辑删除列名
     */
    private static final String DEFAULT_PHYSICAL_DELETE_COLUMN = "ID";

    /**
     * 基础mapper
     */
    private M basisMapper;

    /**
     * 设置基础mapper
     */
    @Autowired
    public void setBasisMapper(M basisMapper) {
        this.basisMapper = basisMapper;
    }

    /**
     * 获取基础mapper
     */
    @Override
    public M getBaseMapper() {
        return basisMapper;
    }

    /**
     * 插入方法
     *
     * @param entity 插入信息
     * @return 执行结果
     */
    @Override
    public int insert(T entity) {
        return this.basisMapper.insert(entity);
    }

    /**
     * 查询方法
     *
     * @param entity entity
     * @return 实体类
     */
    @Override
    public T findOne(T entity) {
        return getById(entity.getId());
    }

    /**
     * 根据条件更新一条记录
     *
     * @param entity entity
     * @return 执行结果
     */
    @Override
    public int update(T entity) {
        return this.basisMapper.updateById(entity);
    }

    /**
     * 获取分页信息
     *
     * @param pageQuery 分页信息
     * @param entity    查询条件
     * @return 分页结果
     */
    @Override
    public PageResult<T> getPage(PageQuery pageQuery, T entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * 将IPage转换为统一分页结果
     * 这里不用转换器，而使用set方法，主要还是为了效率考虑
     *
     * @param iPage 分页信息
     * @return 统一分页结果
     */
    protected PageResult<T> pageResult(IPage<T> iPage) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNum(iPage.getCurrent());
        pageResult.setPageSize(iPage.getSize());
        pageResult.setTotal(iPage.getTotal());
        pageResult.setPages(iPage.getPages());
        pageResult.setRecords(iPage.getRecords());
        return pageResult;
    }

    /**
     * 查询并将查询结果转换为统一分页结果
     *
     * @param page         分页信息
     * @param queryWrapper 查询条件
     * @param <E>          IPage
     * @return 统一分页结果
     */
    protected <E extends IPage<T>> PageResult<T> pageResult(E page, Wrapper<T> queryWrapper) {
        E iPage = page(page, queryWrapper);
        return pageResult(iPage);
    }

    /**
     * 查询并将查询结果转换为统一分页结果
     *
     * @param pageQuery    分页信息
     * @param queryWrapper 查询条件
     * @return 统一分页结果
     */
    protected PageResult<T> pageResult(PageQuery pageQuery, Wrapper<T> queryWrapper) {
        Page<T> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        return pageResult(page, queryWrapper);
    }

    @Override
    public int delete(Collection<? extends Serializable> keyList) {
        return basisMapper.deleteBatchIds(keyList);
    }

    /**
     * 删除方法
     *
     * @param id id
     * @return 执行结果
     */
    @Override
    public int logicDeleteById(Long id) {
        return basisMapper.deleteById(id);
    }

    /**
     * 根据编码批量删除
     *
     * @param identifiers 标识符
     * @return 执行结果
     */
    @Override
    public int logicDeleteBatchByIds(List<Long> identifiers) {
        return delete(identifiers);
    }

    /**
     * 根据ID物理删除
     *
     * @param id ID
     * @return 执行结果
     */
    @Override
    public int physicalDeleteById(Serializable id) {
        return physicalDelete(DEFAULT_PHYSICAL_DELETE_COLUMN, id);
    }

    /**
     * 根据ID批量物理删除
     *
     * @param idList ID集合
     * @return 执行结果
     */
    @Override
    public int physicalDeleteBatchByIds(Collection<? extends Serializable> idList) {
        return physicalDeleteBatch(DEFAULT_PHYSICAL_DELETE_COLUMN, idList);
    }

    /**
     * 根据列名物理删除
     *
     * @param column 列名
     * @param item   值
     * @return 执行结果
     */
    @Override
    public int physicalDelete(String column, Serializable item) {
        return basisMapper.physicalDelete(getCurrentTableName(), column, item);
    }

    /**
     * 根据列名批量物理删除
     *
     * @param column 列名
     * @param list   集合
     * @return 执行结果
     */
    @Override
    public int physicalDeleteBatch(String column, Collection<? extends Serializable> list) {
        return basisMapper.physicalDeleteBatch(getCurrentTableName(), column, list);
    }

    /**
     * 获取当前类的数据库表名
     */
    private String getCurrentTableName() {
        return AnnotationUtil.getAnnotationValue(currentModelClass(), TableName.class);
    }
}
