package com.ies.jetty;

 
import org.eclipse.jetty.server.Server;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class App {
 
        public static void main(String[] args) throws Exception {
         
        Server server = new Server(8680);       
         
        ServletHpublic static void main(String[] args) throws Exception {
              Server server = new Server(8680);
              try {
         server.start();
         server.dumpStdErr();
             server.join();
          } catch (Exception e) {           
            e.printStackTrace();
          }  
        }andler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
                 
        servletHandler.addServletWithMapping(HelloServlet.class, "/");
         
        server.start();
        server.join();
 
    }
     
    public static class HelloServlet extends HttpServlet 
    {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>New Hello Simple Servlet</h1>"); 
               } 
        }
}
