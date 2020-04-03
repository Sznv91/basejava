package ru.topjava.basejava.web;

import ru.topjava.basejava.Config;
import ru.topjava.basejava.model.ContactType;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.storage.Storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ResumeServlet extends javax.servlet.http.HttpServlet {

    private Storage storage = Config.getInstance().getSqlStorageInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset:UTF-8");
        String uuid = request.getParameter("uuid");
        if (uuid == null) {
            response.getWriter().println("Hello Resumes " +
                    "<br> for display all resumes from SQL DB, use parameter <a href=\"resume?uuid=ALL\">?uuid=All<a>" +
                    "<br> <a href=\".\">go back<a>");
        } else {
            if (uuid.equals("ALL")) {
                List<Resume> resumeList = storage.getAllSorted();
                response.getWriter().println("<table cellpadding=\"5\" border=\"5\" style=\"border-collapse:collapse\" bgcolor=\"#a9a9a9\"><tr bgcolor=\"red\"><td>UUID:</td><td>Full Name:</td></tr>");
                for (Resume resume : resumeList) {
                    response.getWriter().println("<tr><td>" + resume.getUuid() + "</td><td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "<a></td></tr>");
                }
                response.getWriter().println("</table>");
            } else {
                Resume resume = storage.get(uuid);
                response.getWriter().println("Hi " + resume.getFullName() + "!");
                response.getWriter().write("<table border=\"1\" bgcolor=\"#a9a9a9\">" +
                        "\n<tr bgcolor=\"red\"><td>Контакт</td><td>Значение</td></tr>");
                for (Map.Entry<ContactType, String> contact : resume.getContacts().entrySet()) {
                    response.getWriter().write("\n<tr> <td>" + contact.getKey().getTitle() + "</td> <td>" + contact.getValue() + "</td> </tr>");
                }
                response.getWriter().write("\n</table>");
            }
            response.getWriter().println("<br><a href=\"resume\">go back<a>");
        }
    }
}
