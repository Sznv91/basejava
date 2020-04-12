<%@ page import="ru.topjava.basejava.model.Resume" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mihail_Sazonov
  Date: 06.04.2020
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>All Resumes List</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<button onclick="">New Resume</button>
<section>
    <br><table>
        <tr>
            <th>UUID:</th>
            <th>Full Name:</th>
            <th>Action:</th>
        </tr>
        <c:forEach var="resume" items="${resumesList}">
            <jsp:useBean id="resume" type="ru.topjava.basejava.model.Resume"/>
            <tr>
                <td><c:out value="${resume.uuid}"/></td>
                <td><a href="resume?uuid=${resume.uuid}&action=view"><c:out value="${resume.fullName}"/></a></td>
                <td><a href="resume?uuid=${resume.uuid}&action=delete"><c:out value="delete"/></a>
                <a href="resume?uuid=${resume.uuid}&action=edit"><c:out value="edit"/></a></td>
            </tr>
        </c:forEach>
        <%-- <% List<Resume> storage = (ArrayList<Resume>) request.getAttribute("ResumesList"); %>
        <% for (Resume resume : storage) { %>
        <tr>
            <td><%=resume.getUuid()%>
            </td>
            <td><a href="resume?uuid=<%=resume.getUuid()%>"><%=resume.getFullName()%>
            </a></td>
        </tr>
        <% } %>--%>
    </table>
</section>
<br>
<button onclick="window.history.back()">Back</button>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
