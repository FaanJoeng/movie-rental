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

        if(req.getRequestURI().contains("/public") || req.getRequestURI().contains("/session")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (req.getSession().getAttribute("user") == null){
            resp.sendRedirect("/session");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }



    }

    @Override
    public void destroy() {

    }
}
