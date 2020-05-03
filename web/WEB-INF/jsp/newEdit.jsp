<%--
  Created by IntelliJ IDEA.
  User: Mihail_Sazonov
  Date: 13.04.2020
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.topjava.basejava.model.CompanySection" %>
<%@ page import="ru.topjava.basejava.model.ContactType" %>
<%@ page import="ru.topjava.basejava.model.ListSection" %>
<%@ page import="ru.topjava.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="resume" type="ru.topjava.basejava.model.Resume" scope="request"/>
<html>
<head>
    <title>${action} ${resume.fullName} </title>
    <link rel="stylesheet" href="css/style.css">
    <script language="JavaScript">
        var items = 1;
        function AddItem(sectionType) {
            div = document.getElementById("items");
            button = document.getElementById("add");
            orgName = "<strong>Название огранизации: </strong>";
            orgName += "<input type = \"text\" name =" + sectionType;
            orgName += " size = \"45\"><br>";
            newNode = document.createElement("span");
            newNode.innerHTML = orgName;
            div.insertBefore(newNode,button);

            div = document.getElementById("items");
            button = document.getElementById("add");
            orgUrl = "<strong>URL: </strong>";
            orgUrl += "<input type = \"text\" name =" + sectionType+'CompanyURL';
            orgUrl += " size = \"45\"><br>";
            newNode = document.createElement("span");
            newNode.innerHTML = orgUrl;
            div.insertBefore(newNode,button);

            div = document.getElementById("items");
            button = document.getElementById("add");
            posStartDate = "<strong>StartDate: </strong>";
            posStartDate += "<input type = \"text\" name =" + sectionType+'PositionStartDate';
            posStartDate += " size = \"45\"><br>";
            newNode = document.createElement("span");
            newNode.innerHTML = posStartDate;
            div.insertBefore(newNode,button);

            div = document.getElementById("items");
            button = document.getElementById("add");
            posEndDate = "<strong>EndDate: </strong>";
            posEndDate += "<input type = \"text\" name =" + sectionType+'PositionEndDate';
            posEndDate += " size = \"45\"><br>";
            newNode = document.createElement("span");
            newNode.innerHTML = posEndDate;
            div.insertBefore(newNode,button);

            div = document.getElementById("items");
            button = document.getElementById("add");
            posTitle = "<strong>PositionTitle: </strong>";
            posTitle += "<input type = \"text\" name =" + sectionType+'PositionTitle';
            posTitle += " size = \"45\"><br>";
            newNode = document.createElement("span");
            newNode.innerHTML = posTitle;
            div.insertBefore(newNode,button);

            div = document.getElementById("items");
            button = document.getElementById("add");
            posDescription = "<strong>Description: </strong>";
            posDescription += "<input type = \"text\" name =" + sectionType+'PositionDescription';
            posDescription += " size = \"45\"><br>";
            newNode = document.createElement("span");
            newNode.innerHTML = posDescription;
            div.insertBefore(newNode,button);
        }
    </script>
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
                        <br> Company Name: <input type="text" name="${section.name()}" value="${company.name}"
                                                  size="60%">
                        URL: <input type="text" name="${section.name()}CompanyURL" value="${company.url}" size="60%">
                        <c:forEach items="${company.positionsList}" var="position">
                            <br> Title: <input type="text" name="${section.name()}PositionTitle"
                                               value="${position.title}">
                            Description: <input type="text" name="${section.name()}PositionDescription"
                                                value="${position.description}">
                            <br> Year(start -> end): <input type="text" name="${section.name()}PositionStartDate"
                                                            value="${position.startDate}">
                            <input type="text" name="${section.name()}PositionEndDate" value="${position.endDate}">
                        </c:forEach>

                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <div ID = "items">
            <input type="button" value="Добавить поле EXPERIENCE" onclick="AddItem('EXPERIENCE');" ID="add">
            <input type="button" value="Добавить поле EDUCATION" onclick="AddItem('EDUCATION');" ID="add">
        </div>

    </section>
    <button type onclick="window.history.back()">Back</button>
    <button type="submit">Save</button>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
