package com.github.d3.data.jdbc.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 自定义租户控制
 *
 * @author Carzer1020@163.com
 * @since 2020-11-19
 */
@Slf4j
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantLineInterceptor extends TenantLineInnerInterceptor {

    /**
     * 租户处理器
     */
    private final TenantLineHandler tenantLineHandler;

    /**
     * 构造方法
     *
     * @param tenantLineHandler 租户handler
     */
    public TenantLineInterceptor(final TenantLineHandler tenantLineHandler) {
        super(tenantLineHandler);
        this.tenantLineHandler = tenantLineHandler;
    }

    /**
     * beforeQuery
     *
     * @param executor      执行器
     * @param ms            映射信息
     * @param parameter     执行参数
     * @param rowBounds     行绑定
     * @param resultHandler 结果handler
     * @param boundSql      绑定的sal
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (ignoreOnce()) {
            if (log.isDebugEnabled()) {
                log.debug("beforeQuery [{}]忽略租户控制", ms.getId());
            }
        } else {
            super.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
        }
    }

    /**
     * beforePrepare
     *
     * @param sh                 StatementHandler
     * @param connection         连接信息
     * @param transactionTimeout timeout
     */
    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        if (ignoreOnce()) {
            if (log.isDebugEnabled()) {
                PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
                log.debug("beforePrepare [{}]忽略租户控制", mpSh.mappedStatement().getId());
            }
        } else {
            super.beforePrepare(sh, connection, transactionTimeout);
        }
    }
//
//    /**
//     * 处理条件
//     */
//    @Override
//    protected Expression builderExpression(Expression currentExpression, List<Table> tables) {
//        // 没有表需要处理直接返回
//        if (CollectionUtils.isEmpty(tables)) {
//            return currentExpression;
//        }
//        // 过滤需要处理的table
//        List<Table> injectTables = tables.stream()
//                .filter(table -> !tenantLineHandler.ignoreTable(table.getName()))
//                .toList();
//        if (CollectionUtils.isEmpty(injectTables)) {
//            return currentExpression;
//        }
//        // 租户
//        Expression tenantId = tenantLineHandler.getTenantId();
//        // 构造每张表的条件
//        List<EqualsTo> equalsTos = injectTables.stream()
//                .map(item -> new EqualsTo(getAliasColumn(item), tenantId))
//                .toList();
//        // 注入的表达式
//        Expression injectExpression = equalsTos.get(0);
//        // 如果有多表，则用 and 连接
//        if (equalsTos.size() > 1) {
//            for (int i = 1; i < equalsTos.size(); i++) {
//                injectExpression = new AndExpression(injectExpression, equalsTos.get(i));
//            }
//        }
//
//        if (currentExpression == null) {
//            return injectExpression;
//        }
//        if (currentExpression instanceof OrExpression) {
//            return new AndExpression(new Parenthesis(currentExpression), injectExpression);
//        } else {
//            return new AndExpression(currentExpression, injectExpression);
//        }
//    }

    /**
     * 忽略这次构建
     *
     * @return 是否忽略
     */
    private boolean ignoreOnce() {

        return false;
    }
}
