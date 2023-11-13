package com.github.d3.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

/**
 * 字符串工具
 *
 * @author Carzer1020@163.com
 * @since 2020-12-04
 */
@UtilityClass
public class StrUtil {

    /**
     * 小写首字母
     *
     * @param str 字符串
     * @return 首字母小写
     */
    public String lowerCaseFirstLetter(String str) {
        return Character.isLowerCase(str.charAt(0)) ? str : Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 大写首字母
     *
     * @param str 字符串
     * @return 首字母大写
     */
    public String upperCaseFirstLetter(String str) {
        return Character.isUpperCase(str.charAt(0)) ? str : Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 判断是否数据类型
     *
     * @param str str
     * @return 是否数据类型
     */
    public boolean isArrayTypeJson(String str) {
        return StringUtils.hasText(str) && str.startsWith("[") && str.endsWith("]");
    }

    /**
     * 获取下一个36进制数字
     *
     * @param num 当前数字
     * @return 下一个36进制数字
     */
    public String next36RadixNum(String num) {
        long next = Long.parseLong(num, 36) + 1;
        return Long.toString(next, 36).toUpperCase();
    }
}
