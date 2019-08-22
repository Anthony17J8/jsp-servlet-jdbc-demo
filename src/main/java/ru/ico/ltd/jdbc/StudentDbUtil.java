package ru.ico.ltd.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    void addStudent(Student newStudent) throws Exception {

        // create sql for insert
        String sql = "insert into student "
                + "(first_name, last_name, email) "
                + "values(?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {

            // set the param values for the student
            st.setString(1, newStudent.getFirstName());
            st.setString(2, newStudent.getLastName());
            st.setString(3, newStudent.getEmail());

            // execute sql insert
            st.execute();
        }
    }
}
