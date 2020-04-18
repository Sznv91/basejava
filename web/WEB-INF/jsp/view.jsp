<%@ page import="ru.topjava.basejava.model.CompanySection" %>
<%@ page import="ru.topjava.basejava.model.ListSection" %>
<%@ page import="ru.topjava.basejava.model.TextSection" %>
<%--
  Created by IntelliJ IDEA.
  User: Mihail_Sazonov
  Date: 06.04.2020
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="resume" type="ru.topjava.basejava.model.Resume" scope="request"/>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
Имя: ${resume.fullName}
<section>
    <table>
        <tr>
            <th>Контакт</th>
            <th>Значение</th>
        </tr>

        <c:forEach var="contact" items="${resume.contacts}">
            <tr>
                <td>${contact.key.title}
                </td>
                <td>${contact.value}
                </td>
            </tr>
        </c:forEach>

        <%--<% for (java.util.Map.Entry<ContactType, String> contact : resume.getContacts().entrySet()) { %>
        <tr>
            <td><%=contact.getKey().getTitle()%>
            </td>
            <td><%=contact.getValue()%>
            </td>
        </tr>
        <%}%>
        --%>
    </table>

    <c:forEach var="section" items="${resume.sections}">

        <jsp:useBean id="section"
                     type="java.util.Map.Entry<ru.topjava.basejava.model.SectionType, ru.topjava.basejava.model.AbstractSection>"/>
        <c:set var="keyName" value="${section.key.name()}"/>
        <c:set var="keyTitle" value="${section.key.title}"/>

        <c:choose>
            <c:when test="${keyName=='PERSONAL'||keyName=='OBJECTIVE'}">
                <table>
                    <tr>
                        <th>${keyTitle}</th>

                    </tr>
                    <tr>
                        <td><c:out value="<%=(TextSection) section.getValue()%>"/></td>
                    </tr>
                </table>
            </c:when>

            <c:when test="${keyName=='ACHIEVEMENT'||keyName=='QUALIFICATIONS'}">
                <table>
                    <tr>
                        <th>${keyTitle}</th>
                    </tr>
                    <c:forEach items="<%=((ListSection) section.getValue()).getContent()%>" var="listContent">
                        <tr>
                            <td><c:out value="${listContent}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

            <c:when test="${keyName=='EXPERIENCE'||keyName=='EDUCATION'}">
                <table>
                    <tr>
                        <th>${keyTitle}</th>
                        <th>URL:</th>
                        <th>Позиция:</th>
                        <th>Период:</th>
                    </tr>
                    <c:forEach items="<%=((CompanySection) section.getValue()).getCompanies()%>" var="companysList">
                        <tr>
                            <td><c:out value="${companysList.name}"/></td>
                            <td><c:out value="${companysList.url}"/></td>
                            <td><c:forEach var="position" items="${companysList.positionsList}">
                                ${position.title} <br>
                                <hr noshade size="1">
                                ${position.description}
                            </c:forEach></td>
                            <td><c:forEach var="position" items="${companysList.positionsList}">
                                ${position.startDate}/${position.endDate}
                                <br>
                                <hr noshade size="1">
                            </c:forEach></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

        </c:choose>

    </c:forEach>
</section>
<button onclick="window.history.back()">Back</button>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
