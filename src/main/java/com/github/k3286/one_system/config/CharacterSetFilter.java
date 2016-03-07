package com.github.k3286.one_system.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterSetFilter implements Filter {

    private static final String UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestCharEncoding");
        if (encoding == null) {
            encoding = UTF8;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Honour the client-specified character encoding
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        /**
         * Set the default response content type and encoding
         */
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(UTF8);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}