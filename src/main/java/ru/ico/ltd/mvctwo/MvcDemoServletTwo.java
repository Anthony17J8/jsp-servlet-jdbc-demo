package ru.ico.ltd.mvctwo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MvcDemoServletTwo")
public class MvcDemoServletTwo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // step 1: get the student data from helper class (model)
        List<Student> students = StudentDataUtil.getStudents();

        // step 2: add students to request object
        request.setAttribute("student_list", students);

        // step 3: get request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view_students_two.jsp");

        // step 4: forward to JSP
        dispatcher.forward(request, response);
    }
}
