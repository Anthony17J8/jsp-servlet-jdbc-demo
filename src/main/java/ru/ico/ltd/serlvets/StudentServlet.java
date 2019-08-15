package ru.ico.ltd.serlvets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Step 1: set content type
        resp.setContentType("text/html");

        // Step 2: get the PrintWriter
        PrintWriter out = resp.getWriter();

        // Step 3: generate the HTML content
        out.println("<html><body>");
        out.println("The student is confirmed: "
                + req.getParameter("firstName") + " "
                + req.getParameter("lastName"));
        out.println("</body></html>");
    }
}
