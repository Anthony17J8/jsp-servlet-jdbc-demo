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

    Student getStudent(String theStudentId) throws Exception {

        // create sql to get selected student
        String sql = "select * from student where id=?";
        Student result = null;
        ResultSet rs = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            // convert student id to int
            int id = Integer.parseInt(theStudentId);

            // set params
            ps.setInt(1, id);

            // execute statement
            rs = ps.executeQuery();

            // retrieve data from result set row
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                // use the studentId during construction
                result = new Student(id, firstName, lastName, email);
            } else {
                throw new Exception("Could not find student id: " + id);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }

    void updateStudent(Student theStudent) throws Exception {

        // create SQL for update
        String sql = "update student set first_name=?, last_name=?, email=? where id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // set params
            preparedStatement.setString(1, theStudent.getFirstName());
            preparedStatement.setString(2, theStudent.getLastName());
            preparedStatement.setString(3, theStudent.getEmail());
            preparedStatement.setInt(4, theStudent.getId());

            // execute SQL statement
            preparedStatement.execute();
        }
    }

    void deleteStudent(String studentId) throws Exception {

        // create sql to delete student
        String sql = "delete from student where id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // convert student id to int
            int id = Integer.parseInt(studentId);

            // set params
            preparedStatement.setInt(1, id);

            // execute sql statement
            preparedStatement.execute();
        }
    }
}
