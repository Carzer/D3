package com.github.d3.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * List操作相关的工具类
 *
 * @author Carzer1020@163.com
 * @since 2020-09-25
 */
@UtilityClass
public class ListUtil {

    /**
     * 获取source比target多出的元素
     *
     * @param source 源
     * @param target 目标
     * @return 多出的元素
     */
    public <T> List<T> more(List<T> source, List<T> target) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        if (CollectionUtils.isEmpty(target)) {
            return source;
        }
        return source.stream().filter(identifier -> !(target.contains(identifier))).toList();
    }

    /**
     * 将list中的内容平铺：根据逗号连接
     * <p>
     * 例:
     * <p>
     * {@code ListUtil.tileList(List<OrgEntity> list,OrgEntity::getCode)}
     * <p>
     * {@code return "code1,code2,code3"}
     *
     * @param list list
     * @param func 平铺方法
     * @param <T>  T
     * @return 平铺后的内容
     */
    public <T> String tileList(List<T> list, Function<T, ? extends Serializable> func) {
        return tileList(list, func, ",");
    }

    /**
     * 将list中的内容平铺：根据分隔符连接
     *
     * @param list      list
     * @param func      平铺方法
     * @param separator 分隔符
     * @param <T>       T
     * @return 平铺后的内容
     */
    public <T> String tileList(List<T> list, Function<T, ? extends Serializable> func, String separator) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        var stringBuilder = new StringBuilder();
        list.forEach(t -> stringBuilder.append(func.apply(t)).append(separator));
        return stringBuilder.substring(0, stringBuilder.lastIndexOf(separator));
    }
}
