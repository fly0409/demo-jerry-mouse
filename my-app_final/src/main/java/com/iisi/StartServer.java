package com.iisi;

import com.iisi.connector.HttpConnector;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Executor;

public class StartServer implements AutoCloseable{
    final Logger logger = LoggerFactory.getLogger(getClass());
    com.sun.net.httpserver.HttpServer httpServer;
    final String host;
    final int port;

    public StartServer(String host, int port, Config config, String webRoot, Executor executor, ClassLoader classLoader, List<Class<?>> autoScannedClasses) throws IOException {
        this.host = host;
        this.port = port;
        this.httpServer = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8080), 0);
        this.httpServer.createContext("/", new HttpConnector(config,webRoot,classLoader,autoScannedClasses));
        this.httpServer.setExecutor(executor); // creates a default executor
        this.httpServer.start();
        logger.info("start jerrymouse http server at {}:{}", host, port);
    }

    public StartServer(HttpServer httpServer, String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void close() {
        this.httpServer.stop(3);
    }
}
