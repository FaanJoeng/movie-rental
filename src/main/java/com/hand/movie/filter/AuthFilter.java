package com.hand.movie.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Fan Yang
 * @since 2017/8/27
 */
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getRequestURI().contains("/")) {
            filterChain.doFilter(req, resp);
        }


        //如果请求的是登录页或者静态资源则不拦截
        if (req.getServletPath().contains("/session") && req.getMethod().toLowerCase().equals("get")) {
            filterChain.doFilter(req, resp);
        }

        //如果Session存在user属性则放行， 不存在则重定向到登录页
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/session");
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
