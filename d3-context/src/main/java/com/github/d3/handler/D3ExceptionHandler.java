package com.github.d3.handler;


import com.github.d3.R;
import com.github.d3.code.RCode;
import com.github.d3.exception.AbstractD3Exception;
import com.github.d3.util.NetUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * 全局exception捕捉
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
@Slf4j
@RestControllerAdvice
public final class D3ExceptionHandler {

    /**
     * 异常捕获：自定义异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AbstractD3Exception.class)
    public R<String> executeFailed(HttpServletRequest request, AbstractD3Exception e) {
        urlInfo(request);
        log.error("业务异常：{}", e.getCode().getMessage());
        return new R<>(e.getCode());
    }

    /**
     * 异常捕获：未授权
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public R<String> authFailed(HttpServletRequest request, AuthenticationException e) {
        log.warn("未授权：{}", e.getMessage());
        debugInfo(request, e);
        return new R<>(RCode.UNAUTHORIZED);
    }

    /**
     * 异常捕获：权限拒绝
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public R<String> forbidden(HttpServletRequest request, AccessDeniedException e) {
        log.warn("权限拒绝：{}", e.getMessage());
        debugInfo(request, e);
        return new R<>(RCode.FORBIDDEN);
    }

    /**
     * 异常捕获：请求异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<String> validFailed(HttpServletRequest request, HttpMessageNotReadableException e) {
        log.warn("非法操作:{}", e.getMessage());
        debugInfo(request, e);
        return new R<>(RCode.ILLEGAL_OPERATION);
    }

    /**
     * 异常捕获：请求状态异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public R<String> stateFailed(HttpServletRequest request, IllegalStateException e) {
        log.warn("请求状态异常：{}", e.getMessage());
        debugInfo(request, e);
        return R.fail(e.getMessage());
    }

    /**
     * 异常捕获：请求参数异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public R<String> validFailed(HttpServletRequest request, IllegalArgumentException e) {
        urlInfo(request);
        log.warn("请求参数异常：{}", e.getMessage());
        return R.fail(e.getMessage());
    }

    /**
     * 异常捕获：请求参数异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> validFailed(HttpServletRequest request, MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String errorMessage;
        if (CollectionUtils.isEmpty(allErrors)) {
            errorMessage = e.getMessage();
        } else {
            try {
                FieldError fieldError = (FieldError) allErrors.get(0);
                errorMessage = fieldError.getDefaultMessage();
            } catch (Exception exception) {
                log.error("获取[请求参数的异常的详细信息]时发生异常", exception);
                errorMessage = "请求参数异常";
            }
        }
        urlInfo(request);
        log.warn("请求参数异常：{}", errorMessage);
        return R.fail(errorMessage);
    }

    /**
     * 异常捕获：参数校验异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public R<String> validFailed(HttpServletRequest request, ValidationException e) {
        urlInfo(request);
        log.warn("参数校验异常：{}", e.getMessage());
        return R.fail(e.getMessage());
    }

    /**
     * 异常捕获：运行时异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public R<String> runtimeFailed(HttpServletRequest request, RuntimeException e) {
        urlInfo(request);
        log.error("运行时异常：", e);
        return new R<>(RCode.SYSTEM_ERROR);
    }

    /**
     * 异常捕获：运行时异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public R<String> executeFailed(HttpServletRequest request, Exception e) {
        urlInfo(request);
        log.error("系统异常：", e);
        return new R<>(RCode.SYSTEM_ERROR);
    }

    /**
     * 输出异常详细信息
     *
     * @param e 异常
     */
    private void debugInfo(HttpServletRequest request, Exception e) {
        if (log.isDebugEnabled()) {
            urlInfo(request);
            log.error("详细信息为：", e);
        }
    }

    /**
     * url信息
     *
     * @param request 请求
     */
    private void urlInfo(HttpServletRequest request) {
        log.error("########## IP：[{}]，请求url：[{}]时出现异常 ##########", NetUtil.getRemoteIpAddress(request), request.getRequestURI());
    }
}
