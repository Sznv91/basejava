<%--
  Created by IntelliJ IDEA.
  User: Mihail_Sazonov
  Date: 13.04.2020
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.topjava.basejava.model.ContactType" %>
<%@ page import="ru.topjava.basejava.model.SectionType" %>
<%@ page import="ru.topjava.basejava.model.TextSection" %>
<%@ page import="ru.topjava.basejava.model.ListSection" %>
<%@ page import="ru.topjava.basejava.model.CompanySection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="resume" type="ru.topjava.basejava.model.Resume" scope="request"/>
<html>
<head>
    <title>${action} ${resume.fullName} </title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<form method="post" action="resume" enctype="application/x-www-form-urlencoded">
    <section>
        <input type="hidden" name="uuid" value="${resume.uuid}">

        Имя:<input type="text" name="fullName" value="${resume.fullName}">
        <c:forEach items="${ContactType.values()}" var="contact">
            <jsp:useBean id="contact" type="ru.topjava.basejava.model.ContactType"/>
            <br><c:out value="${contact.title}"/> <input type="text" name="${contact.name()}"
                                                         value="${resume.getContact(contact)}">
        </c:forEach>
        <br>
        <hr>

        <c:forEach items="${SectionType.values()}" var="section">
            <jsp:useBean id="section" type="ru.topjava.basejava.model.SectionType"/>
            <c:set var="sectionName" value="${section.name()}"/>
            <c:choose>
                <c:when test="${sectionName == 'PERSONAL' || sectionName == 'OBJECTIVE'}">
                    <br>${section.title}
                    <br><input type="text" name="${section.name()}" value="${resume.getSection(section)}" size="60%">
                </c:when>

                <c:when test="${sectionName == 'ACHIEVEMENT' || sectionName == 'QUALIFICATIONS'}">
                    <br>${section.title}
                    <c:forEach var="listSection" items="<%=((ListSection) resume.getSection(section)).getContent()%>">
                        <br><input type="text" name="${section.name()}" value="${listSection}" size="60%">
                    </c:forEach>
                </c:when>

                <c:when test="${sectionName == 'EXPERIENCE' || sectionName == 'EDUCATION'}">
                    <br>${section.title}
                    <c:forEach var="company" items="<%=((CompanySection) resume.getSection(section)).getCompanies()%>">
                        <br> Company Name: <input type="text" name="${section.name()}" value="${company.name}" size="60%">
                        URL: <input type="text" name="${section.name()}" value="${company.url}" size="60%">
                        <c:forEach items="${company.positionsList}" var="position">
                            <br> Title: <input type="text" name="${section.name()}" value="${position.title}">
                            Description:  <input type="text" name="${section.name()}" value="${position.description}">
                            <br> Year(start -> end): <input type="text" name="${section.name()}" value="${position.startDate}">
                            <input type="text" name="${section.name()}" value="${position.endDate}">
                            <input type="text" onkeyup="this.value()    ">
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>


            <%--<br>${section.title}
            <input type="text" name="${section.name()}" value="${resume.getSection(section)}" size="60%">--%>
        </c:forEach>


    </section>
    <button type onclick="window.history.back()">Back</button>
    <button type="submit">Save</button>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
