package com.github.d3.auth.converter;

import com.github.d3.auth.enums.CredentialsTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * String类型转换CredentialsTypeEnum
 *
 * @author Carzer1020@163.com
 * @since 2023-12-14
 */
@Component
public class StringToCredentialsTypeConverter implements Converter<String, CredentialsTypeEnum> {

    /**
     * 转换方法
     *
     * @param source source
     * @return 枚举类
     */
    @Override
    public CredentialsTypeEnum convert(@NonNull String source) {
        return CredentialsTypeEnum.of(Integer.parseInt(source));
    }
}
