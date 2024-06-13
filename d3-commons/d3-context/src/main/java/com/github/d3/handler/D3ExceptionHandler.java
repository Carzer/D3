package com.github.d3.handler;


import com.github.d3.R;
import com.github.d3.code.CommonCode;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;

/**
 * 全局exception捕捉
 *
 * @author Carzer1020@163.com
 * @since 1.0
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
        error(request, e, "业务异常");
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
        warn(request, e, "未授权");
        return new R<>(CommonCode.UNAUTHORIZED);
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
        debug(request, e, "权限拒绝");
        return new R<>(CommonCode.FORBIDDEN);
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
        debug(request, e, "非法操作");
        return new R<>(CommonCode.ILLEGAL_OPERATION);
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
        debug(request, e, "请求状态异常");
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
        warn(request, e, "请求参数异常");
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
                FieldError fieldError = (FieldError) allErrors.getFirst();
                errorMessage = fieldError.getDefaultMessage();
            } catch (Exception exception) {
                log.error("获取[请求参数的异常的详细信息]时发生异常", exception);
                errorMessage = "请求参数异常";
            }
        }
        warn(request, e, "请求参数异常");
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
        warn(request, e, "参数校验异常");
        return R.fail(e.getMessage());
    }

    /**
     * 异常捕获：运行时异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public R<String> runtimeFailed(HttpServletRequest request, RuntimeException e) {
        error(request, e, "运行时异常");
        return new R<>(CommonCode.SYSTEM_ERROR);
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
        error(request, e, "系统异常");
        return new R<>(CommonCode.SYSTEM_ERROR);
    }

    /**
     * 记录异常信息(debug级别)
     *
     * @param request 请求
     * @param e       异常
     * @param message 信息
     */
    private void debug(HttpServletRequest request, Exception e, String message) {
        if (log.isDebugEnabled()) {
            error(request, e, message);
        }
    }

    /**
     * 记录异常信息(warn级别)
     *
     * @param request 请求
     * @param e       异常
     * @param message 信息
     */
    private void warn(HttpServletRequest request, Exception e, String message) {
        if (log.isWarnEnabled()) {
            error(request, e, message);
        }
    }

    /**
     * 记录异常信息
     *
     * @param request 请求
     * @param e       异常
     * @param message 信息
     */
    private void error(HttpServletRequest request, Exception e, String message) {
        log.error("########## IP:[{}]，请求url:[{}]时出现异常 ##########", NetUtil.getRemoteIpAddress(request), request.getRequestURI());
        log.error(String.format("%s:", Optional.ofNullable(message).orElse("详细信息")), e);
    }
}
