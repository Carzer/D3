package com.github.d3.data.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.d3.data.jdbc.entity.MpBaseEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;

/**
 * base mapper
 *
 * @author Carzer1020@163.com
 * @since 2020-03-24
 */
@Mapper
public interface MpBasisMapper<T extends MpBaseEntity> extends BaseMapper<T> {

    /**
     * 根据列名物理删除
     *
     * @param tableName 表名
     * @param column    列名
     * @param item      值
     * @return 执行结果
     */
    @Delete("delete from ${tableName} where ${column} = #{item}")
    int physicalDelete(@Param("tableName") String tableName, @Param("column") String column, @Param("item") Serializable item);

    /**
     * 根据列名批量物理删除
     *
     * @param tableName 表名
     * @param column    列名
     * @param list      集合
     * @return 执行结果
     */
    @Delete({
            "<script>",
            "delete from ${tableName} where ${column} in",
            "<foreach collection='list' open='(' close=')' index='index' item='item' separator=','>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    int physicalDeleteBatch(@Param("tableName") String tableName, @Param("column") String column, @Param("list") Collection<? extends Serializable> list);

}
