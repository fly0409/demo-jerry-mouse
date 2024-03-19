package com.iisi.engine.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String html;
        StringBuilder htmlBuilder = new StringBuilder();
        if (username == null) {
            htmlBuilder.append("<h1>Index Page</h1>");
            htmlBuilder.append(" <form method=\"post\" action=\"/login\">");
            htmlBuilder.append("     <legend>Please Login</legend>");
            htmlBuilder.append("     <p>User Name: <input type=\"text\" name=\"username\"></p>");
            htmlBuilder.append("     <p>Password: <input type=\"password\" name=\"password\"></p>");
            htmlBuilder.append("     <p><button type=\"submit\">Login</button></p>");
            htmlBuilder.append(" </form>");
            html = htmlBuilder.toString();
        } else {
            htmlBuilder.append("<h1>Index Page</h1>");
            htmlBuilder.append("   <p>Welcome,").append(username).append("!</p>");
            htmlBuilder.append("   <p><a href=\"/logout\">Logout</a></p>");
            htmlBuilder.append("     <p>User Name: <input type=\"text\" name=\"username\"></p>");
            htmlBuilder.append("     <p>Password: <input type=\"password\" name=\"password\"></p>");
            htmlBuilder.append("     <p><button type=\"submit\">Login</button></p>");
            html = htmlBuilder.toString();
        }
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.write(html);
        pw.close();
    }
}
