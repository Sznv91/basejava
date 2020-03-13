package ru.topjava.basejava.web;

import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.storage.Storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends javax.servlet.http.HttpServlet {

    private Storage storage;

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
            storage = SqlStorageInstance.getInstance().getSqlStorageInstance();
            if (uuid.equals("ALL")) {
                List<Resume> resumeList = storage.getAllSorted();
                response.getWriter().println("<table border=\"1\" bgcolor=\"#a9a9a9\"><tr bgcolor=\"red\"><td>UUID:</td><td>Full Name:</td></tr>");
                for (Resume resume : resumeList) {
                    response.getWriter().println("<tr><td>" + resume.getUuid() + "</td><td>" + resume.getFullName() + "</td></tr>");
                }
                response.getWriter().println("</table>");
            } else {
                Resume resume = storage.get(uuid);
                response.getWriter().println("Hi " + resume.getFullName() + "!");
            }
            response.getWriter().println("<br><a href=\"resume\">go back<a>");
        }
    }
}
