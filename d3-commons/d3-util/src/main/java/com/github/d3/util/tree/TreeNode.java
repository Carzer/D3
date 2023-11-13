package com.github.d3.util.tree;

import java.util.List;

/**
 * 树节点
 *
 * @author Carzer1020@163.com
 * @since 2020-12-11
 */
public interface TreeNode<T> {

    /**
     * 获取key
     *
     * @return key
     */
    String getKey();

    /**
     * 设置Key
     *
     * @param key key
     */
    void setKey(String key);

    /**
     * 获取父级Key
     *
     * @return 父级Key
     */
    String getParentKey();

    /**
     * 获取Children
     *
     * @return Children
     */
    List<T> getChildren();

    /**
     * 设置Children
     *
     * @param treeNodes children
     */
    void setChildren(List<T> treeNodes);
}
