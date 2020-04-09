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

<% Resume resume = (Resume) request.getAttribute("Resume"); %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Resume <%=resume.getFullName()%></title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>

    <table border="1" bgcolor="#a9a9a9">
        <tr bgcolor="red">
            <td>Контакт</td>
            <td>Значение</td>
        </tr>
        <% for (java.util.Map.Entry<ContactType, String> contact : resume.getContacts().entrySet()) { %>
        <tr>
            <td><%=contact.getKey().getTitle()%>
            </td>
            <td><%=contact.getValue()%>
            </td>
        </tr>
        <%}%>
    </table>

</section>
<button onclick="window.history.back()">Back</button>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
