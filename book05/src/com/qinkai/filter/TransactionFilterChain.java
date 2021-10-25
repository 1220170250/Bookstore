package com.qinkai.filter;

import com.qinkai.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilterChain implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {

            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();//提交事务
            
        } catch (Exception e) {

            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            //把异常抛给Tomcat服务器
            throw new RuntimeException(e);

        }
    }

    @Override
    public void destroy() {

    }
}
