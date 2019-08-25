package ru.ico.ltd.jdbc;

import javax.sql.DataSource;
import java.sql.*;
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
            processResultSet(students, rs);
            return students;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    private void processResultSet(List<Student> students, ResultSet rs) throws SQLException {
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
    }

    void addStudent(Student newStudent) throws Exception {

        // create sql for insert
        String sql = "insert into student "
                + "(first_name, last_name, email) "
                + "values(?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // set the param values for the student
            ps.setString(1, newStudent.getFirstName());
            ps.setString(2, newStudent.getLastName());
            ps.setString(3, newStudent.getEmail());

            // execute sql insert
            ps.execute();
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
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // set params
            ps.setString(1, theStudent.getFirstName());
            ps.setString(2, theStudent.getLastName());
            ps.setString(3, theStudent.getEmail());
            ps.setInt(4, theStudent.getId());

            // execute SQL statement
            ps.execute();
        }
    }

    void deleteStudent(String studentId) throws Exception {

        // create sql to delete student
        String sql = "delete from student where id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // convert student id to int
            int id = Integer.parseInt(studentId);

            // set params
            ps.setInt(1, id);

            // execute sql statement
            ps.execute();
        }
    }

    List<Student> searchStudents(String theSearchName) throws Exception {
        List<Student> students = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement ps = null;
        try (Connection connection = dataSource.getConnection()) {

            // only search ny name if theSearchName is not empty
            if (theSearchName != null && theSearchName.trim().length() > 0) {

                // create sql to search for students by name
                String sql = "select * from student where lower(first_name) like ? or lower(last_name) like ?";

                // create prepare statement
                ps = connection.prepareStatement(sql);

                // set params
                String theSearchNameLike = "%" + theSearchName + "%";
                ps.setString(1, theSearchNameLike);
                ps.setString(2, theSearchNameLike);
            } else {

                // create sql to get all students
                String sql = "select * from student order by last_name";

                // create prepared statement
                ps = connection.prepareStatement(sql);
            }

            // execute statement
            rs = ps.executeQuery();

            // retrieve data from result set row
            processResultSet(students, rs);

        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return students;
    }
}
