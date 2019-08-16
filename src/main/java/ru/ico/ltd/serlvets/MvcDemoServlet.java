package ru.ico.ltd.serlvets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MvcDemoServlet")
public class MvcDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Step 0: Add data
        String[] students = {"Dino", "Marta", "Crawly", "Betty"};
        request.setAttribute("student_list", students);

        // Step 1: get Request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view-students.jsp");

        // Step 2: forward the request to JSP
        dispatcher.forward(request, response);

    }
}
