package com.github.d3.util;

import cn.hutool.core.bean.BeanUtil;
import com.github.d3.dto.BaseDTO;
import com.github.d3.page.PageAble;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * 分页工具类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@UtilityClass
public class PageUtil {

    /**
     * 将查询信息转换为feign client可用的map
     *
     * @param page 分页信息
     * @param data 查询信息
     * @return 查询map
     */
    public Map<String, Object> pageQueryToMap(PageAble page, BaseDTO... data) {
        Map<String, Object> queryMap = MapUtil.hashMap();
        if (page != null) {
            queryMap.putAll(BeanUtil.beanToMap(page, false, true));
        }
        if (data != null) {
            for (BaseDTO dto : data) {
                queryMap.putAll(BeanUtil.beanToMap(dto, false, true));
            }
        }
        return queryMap;
    }
}
