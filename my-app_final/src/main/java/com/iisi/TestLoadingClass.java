package com.iisi;

import jakarta.servlet.annotation.WebServlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TestLoadingClass {
    public static void main(String[] args) throws Exception {
        String pathToJar = "D:\\practice_code\\jerry-mouse\\";

        File file = new File(pathToJar);
        URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};

        // Create a new class loader with the directory
        URLClassLoader cl = URLClassLoader.newInstance(urls);
        Class clazz = cl.loadClass("com.iisi.servlet.HelloServlet");
        System.out.println(clazz.getName());
        System.out.println(clazz.isAnnotationPresent(WebServlet.class));
    }
}
