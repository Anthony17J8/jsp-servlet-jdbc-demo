package ru.ico.ltd.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class StudentDbUtil {

    private DataSource dataSource;

    StudentDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();

        ResultSet rs = null;
        // get a connection
        // create sql statement
        try (Connection connection = dataSource.getConnection(); // doesn't really close it .. just puts back to connection pool
             Statement st = connection.createStatement()) {

            String sql = "select * from student order by last_name";

            // execute query
            rs = st.executeQuery(sql);

            // process result set
            while (rs.next()) {

                // retrieve data from result set row
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                // create new student object
                Student tempStudent = new Student(id, firstName, lastName, email);

                // add it to the list of students
                students.add(tempStudent);
            }
            return students;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
