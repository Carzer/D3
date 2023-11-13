package com.github.d3.auth.converter;

import com.github.d3.auth.enums.UserTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * String类型转换ExpireStateEnum
 *
 * @author Carzer1020@163.com
 * @since 2021-04-15
 */
@Component
public class StringToUserTypeConverter implements Converter<String, UserTypeEnum> {

    /**
     * 转换方法
     *
     * @param source source
     * @return 过期状态枚举
     */
    @Override
    public UserTypeEnum convert(@NonNull String source) {
        return UserTypeEnum.of(Integer.parseInt(source));
    }
}
