package com.iisi.server.connector;

import com.iisi.server.httpExchange.HttpExchangeAdapter;
import com.iisi.server.servlet.HttpServletRequestImpl;
import com.iisi.server.servlet.HttpServletResponseImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

public class HttpConnector implements HttpHandler {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpExchangeAdapter adapter = new HttpExchangeAdapter(exchange);
        HttpServletRequestImpl request = new HttpServletRequestImpl(adapter);
        HttpServletResponseImpl response = new HttpServletResponseImpl(adapter);
        try {
            process(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("HttpConnector handle~~~");
        String name = request.getParameter("name");
        String html = "<h1>Hello, " + (name == null ? "world" : name) + ".</h1>";
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.write(html);
        pw.close();
    }
}
