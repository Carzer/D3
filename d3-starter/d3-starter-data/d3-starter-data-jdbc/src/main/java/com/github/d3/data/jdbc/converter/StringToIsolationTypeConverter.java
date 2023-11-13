package com.github.d3.data.jdbc.converter;

import com.github.d3.data.jdbc.enums.IsolationTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * String转换隔离类型枚举
 *
 * @author Carzer1020@163.com
 * @since 2021-04-14
 */
@Component
public class StringToIsolationTypeConverter implements Converter<String, IsolationTypeEnum> {

    /**
     * 转换方法
     *
     * @param source source
     * @return 隔离类型
     */
    @Override
    public IsolationTypeEnum convert(@NonNull String source) {
        return IsolationTypeEnum.of(Integer.parseInt(source));
    }
}
