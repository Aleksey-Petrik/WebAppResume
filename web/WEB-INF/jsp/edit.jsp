<%@ page import="com.webapp.model.ContactType" %>
<%@ page import="com.webapp.model.SectionType" %>
<%@ page import="com.webapp.util.HtmlUtil" %>
<%@ page import="com.webapp.model.OrganizationSection" %>
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
                <h3>Sections:</h3>
                <c:forEach var="type" items="<%=SectionType.values()%>">
                    <c:set var="section" value="${resume.getSection(type)}"/>
                    <jsp:useBean id="section" type="com.webapp.model.AbstractSection"/>
                    <h4>${type.title}</h4>
                    <c:choose>
                        <c:when test="${type == 'OBJECTIVE'}">
                            <input type="text" name="${type}" size=75 value="${section.blockDescriptions}"/>
                        </c:when>
                        <c:when test="${type == 'PERSONAL'}">
                            <textarea name="${type}" cols=75 rows=5>${section.blockDescriptions}</textarea>
                        </c:when>
                        <c:when test="${type == 'QUALIFICATIONS' || type == 'ACHIEVEMENT'}">
                            <textarea name="${type}" cols=75 rows=5>${section.blockDescriptions}</textarea>
                        </c:when>
                        <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                            <c:forEach var="organization" items="<%=((OrganizationSection)section).getOrganizations()%>">
                                <dl>
                                    <dt><h4>Наименование:</h4></dt>
                                    <dd><input type="text" name="${type}" size="75" value="${organization.title}"/></dd>
                                </dl>
                                <dl>
                                    <dt><h4>Сайт:</h4></dt>
                                    <dd><input type="text" name="${type}" size="75" value="${organization.url}"/></dd>
                                </dl>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <br>
                <button type="submit">Save</button>
                <button onclick="window.history.back()">Cancel</button>
            </form>
        </section>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
