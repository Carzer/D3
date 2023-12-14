package com.github.d3;

import com.github.d3.code.D3Code;
import com.github.d3.code.RCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.beans.Transient;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 执行结果封装
 *
 * @author Carzer1020@163.com
 * @since 2022-11-25
 */
@Setter
@Getter
public final class R<T> {

    /**
     * 编码
     * <p>
     * {@link RCode}
     */
    @Schema(description = "编码:1000,操作成功")
    private long code;

    /**
     * 返回消息
     */
    @Schema(description = "返回消息")
    private String message;

    /**
     * 返回数据
     */
    @Schema(description = "返回数据")
    private T data;

    /**
     * 不全局弹窗提示，搭配前端使用
     */
    @Schema(description = "前台不全局弹窗提示")
    private Boolean silent;

    /**
     * 私有的空构造方法，防止空参数的new
     */
    private R() {
    }

    /**
     * 构造方法
     *
     * @param code {@link D3Code}
     */
    public R(D3Code code) {
        this(code, null, null);
    }

    /**
     * 构造方法（成功）
     *
     * @param data data
     */
    public R(T data) {
        this(RCode.SUCCESS, data, null);
    }

    /**
     * 构造方法
     *
     * @param code    {@link D3Code}
     * @param data    data
     * @param message 消息
     */
    public R(D3Code code, T data, String message) {
        this(false, code, data, message);
    }

    /**
     * 构造方法
     *
     * @param code    {@link D3Code}
     * @param data    data
     * @param message 消息
     */
    public R(boolean silent, D3Code code, T data, String message) {
        Assert.state(code != null, "返回码不能为空!");
        this.silent = silent;
        this.code = code.getCode();
        this.message = Optional.ofNullable(message).orElse(code.getMessage());
        this.data = data;
    }

    /**
     * success 方法
     *
     * @return 返回成功
     */
    public static <T> R<T> success() {
        return R.success(null, null);
    }

    /**
     * success 方法
     *
     * @param t t
     * @return 返回成功
     */
    public static <T> R<T> success(T t) {
        return R.success(t, null);
    }

    /**
     * success 方法
     *
     * @param message 消息
     * @return 返回成功
     */
    public static <T> R<T> success(String message) {
        return R.success(null, message);
    }

    /**
     * success 方法
     *
     * @param t       t
     * @param message 消息
     * @return 返回成功
     */
    public static <T> R<T> success(T t, String message) {
        return new R<>(RCode.SUCCESS, t, message);
    }

    /**
     * 前端不弹框提示
     *
     * @param <T> t
     * @return 返回成功
     */
    public static <T> R<T> silenceSuccess() {
        return new R<>(true, RCode.SUCCESS, null, null);
    }

    /**
     * fail 方法
     *
     * @return 返回失败
     */
    public static <T> R<T> fail() {
        return R.fail(null, null);
    }

    /**
     * fail 方法
     *
     * @param t t
     * @return 返回失败
     */
    public static <T> R<T> fail(T t) {
        return R.fail(t, null);
    }

    /**
     * fail 方法
     *
     * @param message message
     * @return 返回失败
     */
    public static <T> R<T> fail(String message) {
        return R.fail(null, message);
    }

    /**
     * 前端不弹框提示
     *
     * @param <T> t
     * @return 返回失败
     */
    public static <T> R<T> silenceFail() {
        return new R<>(true, RCode.FAILED, null, null);
    }

    /**
     * fail 方法
     *
     * @param t       t
     * @param message message
     * @return 返回失败
     */
    public static <T> R<T> fail(T t, String message) {
        return new R<>(RCode.FAILED, t, message);
    }

    /**
     * 判断是否成功
     *
     * @return 成功
     */
    public boolean succeed() {
        return RCode.SUCCESS.getCode() == this.code;
    }

    /**
     * 请求成功，且数据不为null
     *
     * @return 请求结果
     */
    public boolean notNull() {
        return succeed() && this.data != null;
    }

    /**
     * 请求成功，且返回结果为true
     *
     * @return 请求结果
     */
    @Transient
    public boolean isTrue() {
        return notNull() && this.data instanceof Boolean d && d;
    }

    /**
     * 请求失败，或者集合为empty
     *
     * @return 请求结果
     */
    @Transient
    public boolean isEmpty() {
        return !notEmpty();
    }

    /**
     * 请求成功，且集合非empty
     *
     * @return 请求结果
     */
    public boolean notEmpty() {
        if (notNull()) {
            if (this.data instanceof Collection<?> collection) {
                return !collection.isEmpty();
            } else if (this.data instanceof Map<?, ?> map) {
                return !map.isEmpty();
            } else {
                throw new UnsupportedOperationException("无法对非集合或map类型进行判断");
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[返回码:%s,返回信息:%s]", this.code, this.message);
    }
}
