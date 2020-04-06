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
<html>
<head>
    <title>All Resumes List</title>
</head>
<body>

<section>
    <% List<Resume> storage = (ArrayList<Resume>) request.getAttribute("ResumesList"); %>
    <table cellpadding="5" border="5" style="border-collapse:collapse" bgcolor="#a9a9a9">
        <tr bgcolor="red">
            <td>UUID:</td>
            <td>Full Name:</td>
        </tr>
        <% for (Resume resume : storage) { %>
        <tr>
            <td><%=resume.getUuid()%>
            </td>
            <td><a href="resume?uuid=<%=resume.getUuid()%>"><%=resume.getFullName()%>
            </a></td>
        </tr>
        <% } %>
    </table>
</section>

</body>
</html>
