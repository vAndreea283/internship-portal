package org.proiectre.proiectre.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.common.CompanyDto;
import org.proiectre.proiectre.ejb.CompanyBean;
import org.proiectre.proiectre.entities.CompanyStatus;

import java.io.IOException;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_COMPANIES"}))

@WebServlet(name = "EditCompany", value = "/EditCompany")
public class EditCompany extends HttpServlet {
    @Inject
    private CompanyBean companyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        CompanyDto company = companyBean.findById(id);
        request.setAttribute("company", company);
        request.getRequestDispatcher("/WEB-INF/pages/editCompany.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        CompanyStatus status = CompanyStatus.valueOf(request.getParameter("status"));

        companyBean.updateCompany(id, name, description, status);

        response.sendRedirect(request.getContextPath() + "/Companies");
    }
}