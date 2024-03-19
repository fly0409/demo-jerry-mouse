package com.iisi.addFilter;

import com.iisi.addFilter.connector.HttpConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Start implements AutoCloseable{

    final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) throws IOException {
        String host = "0.0.0.0";
        int port = 8080;
        Start connector = new Start(host, port);
    }

    final com.sun.net.httpserver.HttpServer httpServer;
    final String host;
    final int port;

    public Start(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.httpServer = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8080), 0);
        this.httpServer.createContext("/", new HttpConnector());
        this.httpServer.setExecutor(null); // creates a default executor
        this.httpServer.start();
        logger.info("start jerrymouse http server at {}:{}", host, port);
    }

    @Override
    public void close() {
        this.httpServer.stop(3);
    }
}
