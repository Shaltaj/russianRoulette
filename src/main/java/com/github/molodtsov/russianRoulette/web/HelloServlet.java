package com.github.molodtsov.russianRoulette.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/wiki/*", "/hello*"})
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("text/html");
        String userAgent = req.getHeader("User-Agent");
        PrintWriter pw = resp.getWriter();
        pw.println("Hello, "+userAgent+"!");
    }
}
