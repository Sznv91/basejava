package ru.topjava.basejava.web;

import ru.topjava.basejava.Config;
import ru.topjava.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Storage storage = Config.getInstance().getSqlStorageInstance();
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
                    req.getRequestDispatcher("WEB-INF/jsp/edit.jsp").forward(req,resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
