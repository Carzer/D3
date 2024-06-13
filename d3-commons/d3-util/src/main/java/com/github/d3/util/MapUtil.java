package com.github.d3.util;


import lombok.experimental.UtilityClass;

import java.util.HashMap;

/**
 * Map工具类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@UtilityClass
public class MapUtil {

    /**
     * HashMap默认的初始容量
     */
    public final int HASHMAP_DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * HashMap较大的初始容量
     */
    public final int HASHMAP_LARGER_INITIAL_CAPACITY = 1 << 10;

    /**
     * HashMap的默认负载因子
     */
    public final float HASHMAP_DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 根据数据大小，计算hashmap的初始值
     * <p>
     * 计算公式:数据大小除以默认负载因子，向上取整后加1
     *
     * @param size 数据大小
     * @return hashmap的初始值
     */
    public int getHashMapInitialCapacity(int size) {
        return (int) Math.ceil(size / HASHMAP_DEFAULT_LOAD_FACTOR) + 1;
    }

    /**
     * 创建一个默认大小的HashMap
     *
     * @param <K> Key
     * @param <V> Value
     * @return 默认大小的HashMap
     */
    public <K, V> HashMap<K, V> hashMap() {
        return new HashMap<>(HASHMAP_DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * 根据传入的数值，创建大小合适的HashMap
     * <p>
     * 最小为 1<<4，16
     *
     * @param size 数据大小
     * @param <K>  Key
     * @param <V>  Value
     * @return 根据数值创建的大小合适的HashMap
     */
    public <K, V> HashMap<K, V> hashMap(int size) {
        int initialCapacity = getHashMapInitialCapacity(size);
        return new HashMap<>(Math.max(initialCapacity, HASHMAP_DEFAULT_INITIAL_CAPACITY));
    }

    /**
     * 创建一个较大的HashMap
     *
     * @param <K> Key
     * @param <V> Value
     * @return 较大的HashMap
     */
    public <K, V> HashMap<K, V> largerHashMap() {
        return new HashMap<>(HASHMAP_LARGER_INITIAL_CAPACITY);
    }
}
