package com.neem.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hello",value = "/hello")
public class hello extends HttpServlet {
    @Override
    public void init() throws ServletException {
        //super.init();
        System.out.println("Init() Method Called!!");
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        out.println("<h1><center>Welcome to service method</center></h1>");
    }

    @Override
    public void destroy() {
        //super.destroy();
    }
}
