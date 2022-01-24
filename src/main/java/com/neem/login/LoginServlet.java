package com.neem.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uemail=req.getParameter("username");
        String pass=req.getParameter("password");
        HttpSession session=req.getSession();
        RequestDispatcher dispatcher=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3307/company","root","");
            PreparedStatement pps= con.prepareStatement("select * from info where email=? and upass =?");
            pps.setString(1,uemail);
            pps.setString(2,pass);
            ResultSet rs=pps.executeQuery();
            if (rs.next())
            {
                session.setAttribute("name",rs.getString("uname"));
                dispatcher=req.getRequestDispatcher("index.jsp");
            }
            else {
                req.setAttribute("status","failed");
                dispatcher=req.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(req,resp);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
