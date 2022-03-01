<%@ page import="com.webapp.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Title</title>
</head>
<body>
<section>
    <table border=1px cellpadding="8" cellspacing="0">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th colspan="2"></th>
        </tr>
        <%
            for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {
        %>
        <tr>
            <td><a href="resume?uuid=<%=resume.getUuid()%>"><%=resume.getFullName()%></a></td>
            <td><%=resume.getContact(ContactType.EMAIL)%></td>
            <td><a href="resume?uuid=<%=resume.getUuid()%>&action=update">Update</a></td>
            <td><a href="resume?uuid=<%=resume.getUuid()%>&action=delete">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
</section>
</body>
</html>
