<%@ page import="com.webapp.model.ContactType" %>
<%@ page import="com.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://webapp.com/functions" %>
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
                <%=HtmlUtil.htmlContacts(contact.getKey().getTitle(), contact.getValue())%>
                <%--${fh:htmlContact(contact.key.title + contact.value)}--%>
                <%--<%=contact.getKey().getTitle()%> <%=contact.getValue()%>--%>
                <br>
            </c:forEach>

            <c:forEach var="section" items="${resume.sections}">
                <jsp:useBean id="section" type="java.util.Map.Entry <com.webapp.model.SectionType, com.webapp.model.AbstractSection>"/>
                <h3>${section.key.title}</h3>
            </c:forEach>

        </section>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
