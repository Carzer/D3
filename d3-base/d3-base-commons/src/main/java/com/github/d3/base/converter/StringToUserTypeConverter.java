package com.github.d3.base.converter;

import com.github.d3.base.enums.UserTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * String类型转换UserTypeEnum
 *
 * @author Carzer1020@163.com
 * @since 1.0
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
