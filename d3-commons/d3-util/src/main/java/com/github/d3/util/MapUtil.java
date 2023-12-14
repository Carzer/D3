package com.github.d3.util;


import lombok.experimental.UtilityClass;

/**
 * Map工具类
 *
 * @author Carzer1020@163.com
 * @since 2021-11-15
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
     * @param dataSize 数据大小
     * @return hashmap的初始值
     */
    public int getHashMapInitialCapacity(int dataSize) {
        return (int) Math.ceil(dataSize / HASHMAP_DEFAULT_LOAD_FACTOR) + 1;
    }
}
