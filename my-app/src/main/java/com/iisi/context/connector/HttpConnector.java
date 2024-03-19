package com.iisi.context.connector;

import com.iisi.context.HttpServletRequestImpl;
import com.iisi.context.HttpServletResponseImpl;
import com.iisi.context.ServletContextImpl;
import com.iisi.context.httpExchange.HttpExchangeAdapter;
import com.iisi.context.servlet.HelloServlet;
import com.iisi.context.servlet.IndexServlet;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HttpConnector implements HttpHandler {
    final Logger logger = LoggerFactory.getLogger(getClass());
    final ServletContextImpl servletContext;

    public HttpConnector() throws IOException {
        this.servletContext = new ServletContextImpl();
        this.servletContext.initialize(List.of(IndexServlet.class, HelloServlet.class));
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpExchangeAdapter adapter = new HttpExchangeAdapter(exchange);
        HttpServletRequestImpl request = new HttpServletRequestImpl(adapter);
        HttpServletResponseImpl response = new HttpServletResponseImpl(adapter);

        try {
            this.servletContext.process(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
