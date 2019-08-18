<%@ page import="java.util.*, ru.ico.ltd.jdbc.*" %>

<html>
<head>
    <title>Student Tracker App</title>
</head>

<body>

<%
    // get the students from the request object (sent by servlet)
    List<Student> students = (List<Student>) request.getAttribute("STUDENT_LIST");
%>

<div id="wrapper">
    <div id="header">
        <h2>FooBar University</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>

            <% for (Student tempStudent : students) { %>
            <tr>
                <td><%= tempStudent.getFirstName()%></td>
                <td><%= tempStudent.getLastName()%></td>
                <td><%= tempStudent.getEmail()%></td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>

</html>