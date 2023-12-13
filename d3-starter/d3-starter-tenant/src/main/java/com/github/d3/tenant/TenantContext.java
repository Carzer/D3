package com.github.d3.tenant;

/**
 * 租户管理运行环境
 *
 * @author Carzer1020@163.com
 * @since 2023-02-17
 */
public final class TenantContext {

    /**
     * 私有构造方法
     */
    private TenantContext() {
        throw new UnsupportedOperationException();
    }

    public static class TenantInfoHolder {
        /**
         * 本地线程共享对象
         */
        private static final ThreadLocal<TenantInfo> TENANT_INFO_HOLDER = new ThreadLocal<>();

        /**
         * get方法
         */
        public static TenantInfo getInfo() {
            return TENANT_INFO_HOLDER.get();
        }

        /**
         * set方法
         */
        public static void setInfo(TenantInfo tenantInfo) {
            TENANT_INFO_HOLDER.set(tenantInfo);
        }

        /**
         * 清除方法
         */
        public static void removeInfo() {
            TENANT_INFO_HOLDER.remove();
        }
    }


}
