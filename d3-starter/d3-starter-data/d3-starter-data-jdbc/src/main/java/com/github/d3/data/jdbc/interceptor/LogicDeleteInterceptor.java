package com.github.d3.data.jdbc.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.github.d3.data.jdbc.constants.JdbcDataBaseConstants;
import com.github.d3.security.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * 逻辑删除Interceptor
 *
 * @author Carzer1020@163.com
 * @since 2022-01-10
 */
@Slf4j
@Component
public class LogicDeleteInterceptor implements InnerInterceptor {

    /**
     * {@link StatementHandler#prepare(Connection, Integer)} 操作前置处理
     * <p>
     * 改改sql啥的
     *
     * @param sh                 StatementHandler(可能是代理对象)
     * @param connection         Connection
     * @param transactionTimeout transactionTimeout
     */
    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
        MappedStatement ms = mpSh.mappedStatement();
        if (SqlCommandType.UPDATE == ms.getSqlCommandType()) {
            PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();
            String sql = mpBs.sql();
            if (ms.getId().contains("delete") && sql.contains(String.format("'%s'", JdbcDataBaseConstants.DELETE_BY_PROPERTY))) {
                mpBs.sql(sql.replace(JdbcDataBaseConstants.DELETE_BY_PROPERTY, AuthUtil.getName()));
            }
        }
    }
}
