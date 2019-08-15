package ru.ico.ltd.serlvets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Step 1: set the content type
        resp.setContentType("text/html");

        // Step 2: get the PrintWriter
        PrintWriter out = resp.getWriter();

        // Step 3: generate HTML content
        out.println("<html><body>");
        out.println("<h2>Hello World!</h2>");
        out.println("<hr>");
        out.println("Time on the server is: " + new Date());
        out.println("</html></body>");
    }
}
