package com.github.d3.auth.converter;

import com.github.d3.auth.enums.UserTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * String类型转换UserTypeEnum
 *
 * @author Carzer1020@163.com
 * @since 2023-12-14
 */
@Component
public class StringToUserTypeConverter implements Converter<String, UserTypeEnum> {

    /**
     * 转换方法
     *
     * @param source source
     * @return 枚举类
     */
    @Override
    public UserTypeEnum convert(@NonNull String source) {
        return UserTypeEnum.of(Integer.parseInt(source));
    }
}
