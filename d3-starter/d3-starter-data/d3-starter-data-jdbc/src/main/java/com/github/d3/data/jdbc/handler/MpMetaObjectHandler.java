package com.github.d3.data.jdbc.handler;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.d3.data.jdbc.constants.JdbcDataBaseConstants;
import com.github.d3.security.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis auto fill
 * {@link TableField#fill()}
 *
 * @author Carzer1020@163.com
 * @since 2020-11-19
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MpMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入填充
     *
     * @param metaObject 数据记录
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("start insert fill ....");
        }
        this.strictInsertFill(metaObject, JdbcDataBaseConstants.CREATE_BY_PROPERTY, AuthUtil::getName, String.class);
        this.strictInsertFill(metaObject, JdbcDataBaseConstants.CREATE_DATE_PROPERTY, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, JdbcDataBaseConstants.VERSION, () -> 0, Integer.class);
    }

    /**
     * 更新填充
     *
     * @param metaObject 数据记录
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("start update fill ....");
        }
        this.strictUpdateFill(metaObject, JdbcDataBaseConstants.UPDATE_BY_PROPERTY, AuthUtil::getName, String.class);
        this.strictUpdateFill(metaObject, JdbcDataBaseConstants.UPDATE_DATE_PROPERTY, LocalDateTime::now, LocalDateTime.class);
    }

}