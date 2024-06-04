package com.github.d3.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

/**
 * 2的n次幂计算常用方法
 *
 * @author Carzer1020@163.com
 * @since 2024-05-27
 */
@UtilityClass
public class PowerOfTwoCalcUtil {

    /**
     * 计算2的n次幂
     *
     * @param n 次幂
     * @return 2的n次幂
     */
    public static int power(int n) {
        Assert.isTrue(n > -1 && n < 31, "仅支持2的0次幂到2的30次幂的计算");
        return 1 << n;
    }

    /**
     * 两值相加，会判断两个值是否都为2的次幂
     *
     * @param a a
     * @param b b
     * @return 计算结果
     */
    public static int add(int a, int b) {
        valid(a, b);
        return a | b;
    }

    /**
     * 检查a值中是否包含b值，b值会判断是否2的次幂
     *
     * @param a a
     * @param b b
     * @return 计算结果
     */
    public static boolean check(int a, int b) {
        valid(b);
        return (a & b) == b;
    }

    /**
     * 从a值中移除b值，b值会判断是否2的次幂
     *
     * @param a a
     * @param b b
     * @return 计算结果
     */
    public static int minus(int a, int b) {
        valid(b);
        return a & (~b);
    }

    /**
     * 判断输入的值是否为2的次幂
     *
     * @param num 需要判断的数字
     * @return 是否为2的次幂
     */
    public static boolean isPowerOfTwo(int num) {
        // 如果n小于等于0，则不是2的次幂
        if (num <= 0) {
            return false;
        }
        // 如果n不是2的次幂，则n-1与n进行按位与操作结果不为0
        return (num & (num - 1)) == 0;
    }

    /**
     * 判断输入的值是否都是2的次幂
     * <p>
     * 当输入多个数值时，有任一数值非2的次幂，抛出异常
     *
     * @param nums 需要判断的数值
     */
    static void valid(int... nums) {
        // 如果传入的内容为空，直接抛出异常
        Assert.state(nums != null && nums.length > 0, "至少需要传入一个数字");
        for (int n : nums) {
            Assert.isTrue(isPowerOfTwo(n), "只支持2的次幂数值的计算");
        }
    }
}
