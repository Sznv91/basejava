<%--
  Created by IntelliJ IDEA.
  User: Mihail_Sazonov
  Date: 13.04.2020
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.topjava.basejava.model.ContactType" %>
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
        <c:forEach items="${ContactType.values()}" var="element">
            <jsp:useBean id="element" type="ru.topjava.basejava.model.ContactType"/>
            <br><c:out value="${element.title}"/> <input type="text" name="${element.name()}"
                                                         value="${resume.getContact(element)}">
        </c:forEach>


    </section>
    <button type onclick="window.history.back()">Back</button>
    <button type="submit">Save</button>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
