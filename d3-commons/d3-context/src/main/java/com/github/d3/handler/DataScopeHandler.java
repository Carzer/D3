package com.github.d3.handler;

import com.github.d3.constants.CommonConstants;

/**
 * 数据权限信息
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public interface DataScopeHandler {

    /**
     * 获取数据范围字段名
     * <p>
     * 默认字段名叫: data_scope
     *
     * @return 数据范围字段名
     */
    default String getDataScopeKey() {
        return CommonConstants.SYSTEM_DATA_SCOPE_KEY;
    }

}
