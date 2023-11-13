package com.github.d3.filter;

import com.github.d3.constants.CommonConstants;
import com.github.d3.constants.TokenConstants;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 允许跨域
 *
 * @author Carzer1020@163.com
 * @since 2019-08-22
 */
@WebFilter(filterName = "CorsFilter ")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    /**
     * filter方法
     *
     * @param req   req
     * @param res   res
     * @param chain chain
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                String.format("Origin, X-Requested-With, Content-Type, Accept, Request-Origion,  %s, %s, %s",
                        CommonConstants.HEADER_SOURCE, CommonConstants.HEADER_ALGORITHM, TokenConstants.AUTHORIZATION));
        chain.doFilter(req, res);
    }
}