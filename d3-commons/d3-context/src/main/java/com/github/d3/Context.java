package com.github.d3;

import com.github.d3.dto.BaseDTO;

/**
 * 工程运行环境
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public final class Context {

    /**
     * 私有构造方法
     */
    private Context() {
        throw new UnsupportedOperationException();
    }

    /**
     * 请求源holder
     */
    public static class SourceHolder {

        /**
         * 本地线程共享对象
         */
        private static final ThreadLocal<String> SOURCE_HOLDER = new ThreadLocal<>();

        /**
         * 私有构造方法
         */
        private SourceHolder() {
        }

        /**
         * get方法
         */
        public static String getSource() {
            return SOURCE_HOLDER.get();
        }

        /**
         * set方法
         */
        public static void setSource(String source) {
            SOURCE_HOLDER.set(source);
        }

        /**
         * 清除方法
         */
        public static void removeSource() {
            SOURCE_HOLDER.remove();
        }

    }

    /**
     * 内部信息holder
     */
    public static class BaseHolder {

        /**
         * 本地线程共享对象
         */
        private static final ThreadLocal<BaseDTO> BASE_HOLDER = new ThreadLocal<>();

        /**
         * 私有构造方法
         */
        private BaseHolder() {
        }

        /**
         * get方法
         */
        public static BaseDTO getInfo() {
            return BASE_HOLDER.get();
        }

        /**
         * set方法
         */
        public static void setInfo(BaseDTO baseInfo) {
            BASE_HOLDER.set(baseInfo);
        }

        /**
         * 清除方法
         */
        public static void removeInfo() {
            BASE_HOLDER.remove();
        }
    }

}
