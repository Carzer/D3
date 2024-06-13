package com.github.d3.util.tree;

import com.github.d3.constants.CommonConstants;
import com.github.d3.util.MapUtil;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 树工具类
 * <p>
 * 如果节点过多，还是会出现性能问题
 *
 * @author Carzer1020@163.com
 * @since 2022-12-11
 */
@UtilityClass
public class TreeUtil {

    /**
     * 将列表转换为树结构
     *
     * @param list 列表
     * @param t    根节点
     * @return 树结构
     */
    public <T extends TreeNode<T>> T transferToTree(List<T> list, T t) {
        Map<String, T> map = MapUtil.hashMap(list.size());
        list.forEach(entity -> map.put(entity.getKey(), entity));
        String root = Optional.ofNullable(t.getKey()).orElse(CommonConstants.ROOT_NUM);
        if (map.containsKey(root)) {
            t = map.get(root);
        } else {
            t.setKey(root);
        }
        Map<String, List<String>> groupMap = list.stream().collect(Collectors.groupingBy(T::getPKey, Collectors.mapping(T::getKey, Collectors.toList())));
        setChildren(t, map, groupMap);
        return t;
    }

    /**
     * 设置children
     *
     * @param t        当前节点
     * @param map      所有节点实体的map {key : t.key，value: t实体}
     * @param groupMap 根据parentKey分组的所有节点编码
     */
    private <T extends TreeNode<T>> void setChildren(T t, Map<String, T> map, Map<String, List<String>> groupMap) {
        // 当前节点key
        String key = t.getKey();
        List<T> children = new ArrayList<>();
        // 查找：以当前节点编码为父编码的，所有子编码
        List<String> childKeyList = groupMap.get(key);
        if (!CollectionUtils.isEmpty(childKeyList)) {
            childKeyList.forEach(childKey -> {
                // 根据子节点编码，获取子节点
                T child = map.get(childKey);
                // 如果子节点依然不是叶子节点，则继续设置children
                if (groupMap.containsKey(childKey)) {
                    setChildren(child, map, groupMap);
                    // 设置完成后，移除该节点，减少检索范围。
                    groupMap.remove(childKey);
                }
                children.add(child);
            });
            t.setChildren(children);
        }
    }

}
