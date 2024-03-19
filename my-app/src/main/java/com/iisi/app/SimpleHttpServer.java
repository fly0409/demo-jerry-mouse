package com.iisi.app;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class SimpleHttpServer implements AutoCloseable{
    final Logger logger = LoggerFactory.getLogger(getClass());

    final HttpServer httpServer;
    final String host;
    final int port;

    public SimpleHttpServer(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        this.httpServer.createContext("/", new SimpleHttpHandler());
        this.httpServer.setExecutor(null); // creates a default executor
        this.httpServer.start();
        logger.info("start jerrymouse http server at {}:{}", host, port);
    }

    @Override
    public void close() {
        this.httpServer.stop(3);
    }


//    public void handle(HttpExchange exchange) throws IOException {
//        String method = exchange.getRequestMethod();
//        URI uri = exchange.getRequestURI();
//        String path = uri.getPath();
//        String query = uri.getRawQuery();
//        logger.info("{}: {}?{}", method, path, query);
//        Headers respHeaders = exchange.getResponseHeaders();
//        respHeaders.set("Content-Type", "text/html; charset=utf-8");
//        respHeaders.set("Cache-Control", "no-cache");
//
//        exchange.sendResponseHeaders(200, 0);
//        String s = "<h1>Hello1, world.</h1><p>" + LocalDateTime.now().withNano(0) + "</p>";
//        try (OutputStream out = exchange.getResponseBody()) {
//            out.write(s.getBytes(StandardCharsets.UTF_8));
//        }
//    }
}
