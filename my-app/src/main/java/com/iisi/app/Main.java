package com.iisi.app;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = "0.0.0.0";
        int port = 8080;
        SimpleHttpServer connector = new SimpleHttpServer(host, port);
    }
}
