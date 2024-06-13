package com.github.d3.filter;

import io.undertow.servlet.util.IteratorEnumeration;
import jakarta.servlet.ReadListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * 请求处理
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public abstract class AbstractRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 参数map
     */
    protected final Map<String, String[]> params = new HashMap<>();

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    protected AbstractRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 获取parameter
     *
     * @param name name
     * @return parameter
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 重写getParameterValues
     *
     * @param name name
     * @return ParameterValues
     */
    @Override
    public String[] getParameterValues(String name) {//同上
        return params.get(name);
    }

    /**
     * 获取ParameterMap
     *
     * @return ParameterMap
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    /**
     * 获取参数名
     *
     * @return ParameterMap
     */
    @Override
    public Enumeration<String> getParameterNames() {
        Set<String> parameterNames = new HashSet<>(this.params.keySet());
        return new IteratorEnumeration<>(parameterNames.iterator());
    }

    /**
     * 是否json类型
     *
     * @param header header信息
     */
    protected boolean isJsonType(String header) {
        return StringUtils.hasText(header) && (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(header)
                || "application/json;charset=UTF-8".equalsIgnoreCase(header));
    }

    /**
     * InputStream
     */
    protected static class ServletInputStream extends jakarta.servlet.ServletInputStream {

        /**
         * InputStream
         */
        private final ByteArrayInputStream bis;

        /**
         * 构造方法
         *
         * @param bis InputStream
         */
        public ServletInputStream(ByteArrayInputStream bis) {
            this.bis = bis;
        }

        /**
         * 是否完成
         *
         * @return 是否完成
         */
        @Override
        public boolean isFinished() {
            return true;
        }

        /**
         * 是否准备状态
         *
         * @return 是否准备状态
         */
        @Override
        public boolean isReady() {
            return true;
        }

        /**
         * 设置读取监听
         *
         * @param listener 读取监听
         */
        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException();
        }

        /**
         * 读取动作
         *
         * @return 数据
         */
        @Override
        public int read() {
            return bis.read();
        }
    }
}
