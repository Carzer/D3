package com.github.d3.util.jackson;


import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应输出工具类
 *
 * @author Carzer1020@163.com
 * @since 2020-01-22
 */
@UtilityClass
public class PrintWriterUtil extends JacksonUtil {

    /**
     * 输出方法
     *
     * @param httpServletResponse httpServletResponse
     * @param o                   输出内容
     * @throws IOException IOException
     */
    public void write(HttpServletResponse httpServletResponse, Object o) throws IOException {
        write(httpServletResponse, o, -1);
    }

    /**
     * 输出方法
     *
     * @param httpServletResponse httpServletResponse
     * @param o                   输出内容
     * @param httpStatus          请求状态
     * @throws IOException IOException
     */
    public void write(HttpServletResponse httpServletResponse, Object o, int httpStatus) throws IOException {
        if (httpStatus > -1) {
            httpServletResponse.setStatus(httpStatus);
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        String s = MAPPER.writeValueAsString(o);
        try (PrintWriter out = httpServletResponse.getWriter()) {
            out.write(s);
            out.flush();
        }
    }
}
