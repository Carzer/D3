package com.github.d3.util;

import com.github.d3.dto.BaseDTO;
import com.github.d3.page.PageResult;
import com.github.d3.util.jackson.JsonUtil;
import com.github.d3.util.tree.TreeNode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 类转换器
 *
 * @author Carzer1020@163.com
 * @since 2019-12-20
 */
@Slf4j
@UtilityClass
public class BeanCopyUtil {

    /**
     * 使用缓存提高效率
     */
    private final ConcurrentHashMap<String, BeanCopier> COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * 复制list
     *
     * @param sourceList  sourceList
     * @param targetClass targetClass
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 目标类list
     */
    public <S, T> List<T> copyList(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream().map(source -> copy(source, targetClass)).collect(Collectors.toList());
    }

    /**
     * 复制树结构
     *
     * @param source      source
     * @param targetClass targetClass
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 目标树
     */
    public <S extends TreeNode<S>, T extends TreeNode<T>> T copyTree(S source, Class<T> targetClass) {
        T target = copy(source, targetClass);
        if (target == null) {
            return null;
        }
        List<S> sourceChildren = source.getChildren();
        if (!CollectionUtils.isEmpty(sourceChildren)) {
            List<T> targetChildren = new ArrayList<>();
            sourceChildren.forEach(child -> targetChildren.add(copyTree(child, targetClass)));
            target.setChildren(targetChildren);
        }
        return target;
    }

    /**
     * 复制并新建类
     *
     * @param source      source
     * @param targetClass targetClass
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 目标类
     */
    public <S, T> T copy(S source, Class<T> targetClass) {
        try {
            T instance = targetClass.getDeclaredConstructor().newInstance();
            return copy(source, instance);
        } catch (Exception e) {
            log.error("创建对象异常:", e);
            return null;
        }
    }

    /**
     * 复制方法
     *
     * @param source source
     * @param target target
     * @param <S>    源类型
     * @param <T>    目标类型
     * @return 目标类
     */
    public <S, T> T copy(S source, T target) {
        if (source != null) {
            try {
                String baseKey = generateKey(source.getClass(), target.getClass());
                BeanCopier copier;
                if (COPIER_CACHE.containsKey(baseKey)) {
                    copier = COPIER_CACHE.get(baseKey);
                } else {
                    copier = BeanCopier.create(source.getClass(), target.getClass(), false);
                    COPIER_CACHE.put(baseKey, copier);
                }
                copier.copy(source, target, null);
            } catch (Exception e) {
                log.error("复制对象属性异常:{}", e.getMessage());
            }
        }
        return target;
    }

    /**
     * 转换为PageResult
     *
     * @param page  分页信息
     * @param clazz 目标类
     * @param <S>   源类型
     * @param <T>   目标类型
     * @return PageResult
     */
    public <S, T extends BaseDTO> PageResult<T> convertPage(PageResult<S> page, Class<T> clazz) {
        Assert.notNull(page, "传入的page不能为空！");
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNum(page.getPageNum());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotal(page.getTotal());
        pageResult.setPages(page.getPages());
        pageResult.setRecords(copyList(page.getRecords(), clazz));
        return pageResult;
    }

    /**
     * 以json形式进行copy
     *
     * @param source      源
     * @param targetClass 目标
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 目标类型对象
     */
    public <S, T> T jsonDeepCopy(S source, Class<T> targetClass) {
        Assert.notNull(source, "传入的source不能为空！");
        try {
            String json = JsonUtil.toJson(source);
            if (StringUtils.hasText(json)) {
                return JsonUtil.fromJson(json, targetClass);
            } else {
                return targetClass.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            log.error("json方式转换对象异常:", e);
            return null;
        }
    }

    /**
     * 生成缓存key
     *
     * @param source source
     * @param target target
     * @return 目标类
     */
    private String generateKey(Class<?> source, Class<?> target) {
        return String.format("%s|%s", source.toString(), target.toString());
    }

}

