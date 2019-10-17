package com.ies.WEBAPP;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(name = "MyFirstServlet", urlPatterns = {"/MyFirstServlet"})
public class servlet_example extends HttpServlet {
 
    private static final long serialVersionUID = -1915463532411657451L;
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        String user = request.getParameter("username");
        String password = request.getParameter("pass");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Object obj = null;
        obj.hashCode();
        out.println("<html>");
            out.println("<head>");
            out.println("<title>HTTP Request example</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Hello "+user+"</h2>");
            out.println("</body>");
            out.println("</html>");
    }
     
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //Do some other work
    }
}