package com.iisi;

import jakarta.servlet.annotation.WebServlet;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class TestLoadingJar {
    public static void main(String[] args) throws Exception {
        String pathToJar = "D:\\practice_code\\jerry-mouse\\hello-webapp-1.0.war";
        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
//        URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.split("classes/")[1];
            className = className.replace('/', '.');
            Class clazz = cl.loadClass(className);

            System.out.println(clazz.getName());
            System.out.println(clazz.isAnnotationPresent(WebServlet.class));

        }
    }


//    public static void main(String[] args) throws Exception {
//        String warpath = "D:\\practice_code\\jerry-mouse\\hello-webapp-1.0.war";
//
//        // Fetch JarFile
//        JarFile jar = new JarFile(warpath);
//
//        // Load .war into the JVM
//        URL url = new URL("jar:file:" + warpath + "!/WEB-INF/classes/");
//        URLClassLoader child = new URLClassLoader(
//                new URL[]{url},
//                Object.class.getClassLoader()
//        );
//
//        // Get class names in the .war
//        Set<String> classes = getClassNamesFromJarFile(jar, "WEB-INF/classes/", ".class");
//
//        // Try to get classes from name
//        for (String className : classes) {
//            try {
//                System.out.println("CLASS FOUND: " + className);
//                Class<?> actualClass= Class.forName(className, true, child);
//            } catch(Error | Exception e) {
//                System.out.println("ERROR: " + e);
//            }
//        }
//    }
//    public static Set<String> getClassNamesFromJarFile(
//            JarFile file, String pfx, String sfx) {
//        Set<String> result = new HashSet<>();
//        Enumeration<JarEntry> enm = file.entries();
//        while (enm.hasMoreElements()) {
//            JarEntry e = enm.nextElement();
//            String path = e.getName();
//            if (path.startsWith(pfx) && path.endsWith(sfx)) {
//                path = path.substring(pfx.length(), path.length()-sfx.length());
//                path = path.replace('/', '.');
//                result.add(path);
//            }
//        }
//        return result;
//    }
}
