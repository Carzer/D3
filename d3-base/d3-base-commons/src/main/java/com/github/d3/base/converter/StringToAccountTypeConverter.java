package com.github.d3.base.converter;

import com.github.d3.base.enums.AccountTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * String类型转换AccountTypeEnum
 *
 * @author Carzer1020@163.com
 * @since 2023-12-14
 */
@Component
public class StringToAccountTypeConverter implements Converter<String, AccountTypeEnum> {

    /**
     * 转换方法
     *
     * @param source source
     * @return 枚举类
     */
    @Override
    public AccountTypeEnum convert(@NonNull String source) {
        return AccountTypeEnum.of(Integer.parseInt(source));
    }
}
