package com.github.d3.page;

import java.io.Serializable;

/**
 * 可分页的信息
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
public sealed interface PageAble extends Serializable permits PageQuery, PageResult {

}
