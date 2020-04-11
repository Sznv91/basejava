<%@ page import="ru.topjava.basejava.model.ContactType" %>
<%@ page import="ru.topjava.basejava.model.Resume" %>
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
</section>
<button onclick="window.history.back()">Back</button>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
