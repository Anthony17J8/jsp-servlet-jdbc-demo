package ru.ico.ltd.serlvets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/TestParamServlet")
public class TestParamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Step 1: set content type
        resp.setContentType("text/html");

        // Step 2: get PrintWriter
        PrintWriter out = resp.getWriter();

        // Step 3: read configuration parameters
        ServletContext context = getServletContext(); // inherit from HttpServlet

        String maxCartSize = context.getInitParameter("max-shopping-cart-size");
        String teamName = context.getInitParameter("project-team-name");

        // Step 4: generate HTML content
        out.println("<html><body>");
        out.println("Max cart: " + maxCartSize);
        out.println("<br/><br/>");
        out.println("Team name: " + teamName);
        out.println("</body></html>");
    }
}
