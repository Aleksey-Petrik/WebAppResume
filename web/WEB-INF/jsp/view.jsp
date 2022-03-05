<%@ page import="com.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.webapp.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <section>
        <h2>Resume for ${resume.fullName}<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></h2>
        <c:forEach  var="contact" items="${resume.contacts}">
            <jsp:useBean id="contact" type="java.util.Map.Entry<com.webapp.model.ContactType, java.lang.String>"/>
            <%=contact.getKey().getTitle()%> <%=contact.getValue()%>
            <br>
        </c:forEach>
    </section>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
