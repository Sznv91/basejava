package ru.topjava.basejava.web;

import ru.topjava.basejava.Config;
import ru.topjava.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Storage storage = Config.getInstance().getSqlStorageInstance();
        try{
            if(!req.getParameter("uuid").isEmpty()) {
                req.setAttribute("Resume", storage.get(req.getParameter("uuid")));
                req.getRequestDispatcher("WEB-INF/uuid.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            req.setAttribute("ResumesList", storage.getAllSorted());
            req.getRequestDispatcher("WEB-INF/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
