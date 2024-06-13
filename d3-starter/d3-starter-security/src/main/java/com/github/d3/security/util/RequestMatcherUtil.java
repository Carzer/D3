package com.github.d3.security.util;

import cn.hutool.core.util.ArrayUtil;
import com.github.d3.constants.PunctuationConstants;
import lombok.experimental.UtilityClass;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求匹配器工具类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@UtilityClass
public class RequestMatcherUtil {

    /**
     * 获取过滤的请求
     *
     * @return 过滤的请求
     */
    public static RequestMatcher[] excludedMatchers(String... excludedPaths) {
        List<RequestMatcher> requestMatchers = antMatchers(excludedPaths);
        return ArrayUtil.toArray(requestMatchers, RequestMatcher.class);
    }

    /**
     * 根据路径创建匹配器
     *
     * @param antPatterns 路径
     * @return 匹配器
     */
    public static List<RequestMatcher> antMatchers(String... antPatterns) {
        List<RequestMatcher> matchers = new ArrayList<>();
        if (ArrayUtil.isNotEmpty(antPatterns)) {
            for (String pattern : antPatterns) {
                if (pattern.contains(PunctuationConstants.BROKEN_BAR)) {
                    String[] arr = pattern.split(PunctuationConstants.BROKEN_BAR);
                    int index = arr.length - 1;
                    String p = arr[0];
                    do {
                        matchers.add(new AntPathRequestMatcher(p, arr[index].toUpperCase()));
                        index--;
                    } while (index > 0);
                } else {
                    matchers.add(new AntPathRequestMatcher(pattern, null));
                }
            }
        }
        return matchers;
    }
}
