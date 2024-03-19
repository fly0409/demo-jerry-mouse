package com.iisi.addFilter.connector;

import com.iisi.addFilter.engine.HttpServletRequestImpl;
import com.iisi.addFilter.engine.HttpServletResponseImpl;
import com.iisi.addFilter.engine.ServletContextImpl;
import com.iisi.addFilter.engine.filter.HelloFilter;
import com.iisi.addFilter.engine.filter.LogFilter;
import com.iisi.addFilter.engine.servlet.HelloServlet;
import com.iisi.addFilter.engine.servlet.IndexServlet;

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
        this.servletContext.initServlets(List.of(IndexServlet.class, HelloServlet.class));
        this.servletContext.initFilters(List.of(LogFilter.class, HelloFilter.class));

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var adapter = new HttpExchangeAdapter(exchange);
        var request = new HttpServletRequestImpl(adapter);
        var response = new HttpServletResponseImpl(adapter);
        // process:
        try {
            this.servletContext.process(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
