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
            <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
                <input name="uuid" type="hidden" value="${resume.uuid}"/>
                <dl>
                    <dt><b>Full Name:</b></dt>
                    <dd><input type="text" name="full_name" size="50" value="${resume.fullName}"/></dd>
                </dl>
                <h3>Contacts:</h3>
                <c:forEach var="type" items="<%=ContactType.values()%>">
                    <dl>
                        <dt><b>${type.title}</b></dt>
                        <dd><input type="text" name="${type.name()}" value="${resume.getContact(type)}" size="30"/></dd>
                    </dl>
                </c:forEach>
                <hr>
                <button type="submit">Save</button>
                <button onclick="window.history.back()">Cancel</button>
            </form>
        </section>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
