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

        //如果路径包含/public(静态资源文件夹)或者/session(登录注销) 不予拦截
        if(req.getRequestURI().contains("public") || req.getRequestURI().contains("session")){
            filterChain.doFilter(req, resp);
        } else if (req.getSession().getAttribute("user") == null){
            resp.sendRedirect("session");
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
