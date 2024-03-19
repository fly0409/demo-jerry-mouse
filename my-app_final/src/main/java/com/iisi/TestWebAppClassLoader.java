package com.iisi;

import com.iisi.classloader.WebAppClassLoader;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.nio.file.Path;

public class TestWebAppClassLoader {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path classPath = Path.of("D:", "practice_code", "jerry-mouse");
        Path aa = classPath.toAbsolutePath();
        WebAppClassLoader wcl = new WebAppClassLoader(classPath, classPath);
        Class clazz = wcl.loadClass("com.iisi.servlet.HelloServlet");
        System.out.println(clazz.getName());
        System.out.println(clazz.isAnnotationPresent(WebServlet.class));

    }
}
