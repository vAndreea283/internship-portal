package org.proiectre.proiectre.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.ejb.CompanyBean;

import java.io.IOException;

@WebServlet(name="RegisterCompany", value="/RegisterCompany")
public class RegisterCompany extends HttpServlet {

    @Inject
    private CompanyBean companyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registerCompany.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String companyName = request.getParameter("company_name");
        String description = request.getParameter("description");

        String error = companyBean.registerCompany(username, email, password, companyName, description);
        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/pages/registerCompany.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/Login");
    }
}