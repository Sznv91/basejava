package ru.topjava.basejava.web;

import ru.topjava.basejava.Config;
import ru.topjava.basejava.model.ContactType;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private Storage storage = Config.getInstance().getSqlStorageInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Storage storage = Config.getInstance().getSqlStorageInstance();
        String uuid = req.getParameter("uuid");
        String action = req.getParameter("action");

        if (action == null) {
            req.setAttribute("resumesList", storage.getAllSorted());
            req.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(req, resp);
        } else {
            switch (action) {
                case "view":
                    req.setAttribute("resume", storage.get(uuid));
                    req.getRequestDispatcher("WEB-INF/jsp/view.jsp").forward(req, resp);
                    break;
                case "delete":
                    storage.delete(uuid);
                    resp.sendRedirect("./resume");
                    break;
                case "edit":
                    req.setAttribute("resume", storage.get(uuid));
                    req.setAttribute("action", "Edit");
                    req.getRequestDispatcher("WEB-INF/jsp/newEdit.jsp").forward(req,resp);
                    break;
                case "new":
                    Resume resume = new Resume("");
                    storage.save(resume);
                    req.setAttribute("resume", resume);
                    req.setAttribute("action", "New");
                    req.getRequestDispatcher("WEB-INF/jsp/newEdit.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uuid = req.getParameter("uuid");
        String fullName = req.getParameter("fullName");
        if (fullName.trim().length()<1){
            storage.delete(uuid);
            resp.sendRedirect("./resume");
            return;
        }
        Resume resume = storage.get(uuid);
        resume.setFullName(fullName);
        for (ContactType type : ContactType.values()){
            String value = req.getParameter(type.name());
            if (value != null && value.trim().length() != 0){
                resume.setContact(type,value);
            } else {
                resume.getContacts().remove(type);
            }
        }
        storage.update(resume);
        resp.sendRedirect("resume?uuid="+uuid+"&action=view");
    }
}
