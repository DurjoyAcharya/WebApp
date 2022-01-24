package com.neem.registration;
import com.mysql.cj.jdbc.Driver;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String pass=req.getParameter("pass");
        String phone=req.getParameter("contact");
        RequestDispatcher dispatcher=null;
        //my database part
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3307/company","root","");
            PreparedStatement pst= con.prepareStatement(
                    "INSERT INTO info(uname, email, upass, contact) VALUES (?,?,?,?)");
            pst.setString(1,name);
            pst.setString(2,email);
            pst.setString(3,pass);
            pst.setString(4,phone);
            int count=pst.executeUpdate();
            dispatcher=req.getRequestDispatcher("registration.jsp");
            if (count>0)
            {
                req.setAttribute("status","success");
               // dispatcher
            }else {
                req.setAttribute("status","failed");
            }
            dispatcher.forward(req,resp);

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
}
