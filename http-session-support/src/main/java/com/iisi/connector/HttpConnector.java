package com.iisi.connector;

import com.iisi.engine.HttpServletRequestImpl;
import com.iisi.engine.HttpServletResponseImpl;
import com.iisi.engine.ServletContextImpl;
import com.iisi.engine.filter.LogFilter;
import com.iisi.engine.servlet.IndexServlet;
import com.iisi.engine.servlet.LoginServlet;
import com.iisi.engine.servlet.LogoutServlet;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class HttpConnector implements HttpHandler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    final ServletContextImpl servletContext;


    public HttpConnector() throws IOException {
        this.servletContext = new ServletContextImpl();
        this.servletContext.initServlets(List.of(IndexServlet.class, LoginServlet.class, LogoutServlet.class));
        this.servletContext.initFilters(List.of(LogFilter.class));

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var adapter = new HttpExchangeAdapter(exchange);
        var response = new HttpServletResponseImpl(adapter);
        var request = new HttpServletRequestImpl(this.servletContext, adapter, response);
        // process:
        try {
            this.servletContext.process(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}