package com.webapp.web;

import com.webapp.storage.Storage;
import com.webapp.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resumes", storage.getAllSorted());
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
        /*
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String uuid = request.getParameter("uuid");
        StringBuilder content = new StringBuilder();
        content.append("<table border=1px><tr><th>UUID</th><th>Full Name</th></tr>");
        if (uuid != null) {
            Resume resume = storage.get(uuid);
            content.append(String.format("<tr><th>%s</th><th>%s</th></tr></table>", resume.getUuid(), resume.getFullName()));
        } else {
            List<Resume> resumes = storage.getAllSorted();
            resumes.forEach(resume -> content.append(String.format("<tr><th>%s</th><th>%s</th></tr>", resume.getUuid(), resume.getFullName())));
            content.append("</table>");
        }
        response.getWriter().write(content.toString());
        */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    }
}
