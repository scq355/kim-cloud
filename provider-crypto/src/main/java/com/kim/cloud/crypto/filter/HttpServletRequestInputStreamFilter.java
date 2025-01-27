package com.kim.cloud.crypto.filter;

import com.kim.cloud.crypto.config.InputStreamHttpServletRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Component
@Order(HIGHEST_PRECEDENCE + 1)
public class HttpServletRequestInputStreamFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 转换为可以多次获取流的request
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        InputStreamHttpServletRequestWrapper inputStreamHttpServletRequestWrapper = new InputStreamHttpServletRequestWrapper(httpServletRequest);

        // 放行
        chain.doFilter(inputStreamHttpServletRequestWrapper, response);
    }
}
