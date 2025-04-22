package com.wth.msgmp_api.filter;
import com.wth.msgmp_api.config.UrlProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 攔截器，參數組裝
 */
@WebFilter("/*")
@AllArgsConstructor
@EnableConfigurationProperties({UrlProperties.class})
public class RequestFilter implements Filter {

    private final UrlProperties urlProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setAttribute("springUrl",urlProperties.getRequestUrl());
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
