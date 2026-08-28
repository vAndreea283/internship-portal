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
import org.proiectre.proiectre.common.PositionDto;
import org.proiectre.proiectre.common.UserDto;
import org.proiectre.proiectre.ejb.CompanyBean;
import org.proiectre.proiectre.ejb.UserBean;
import org.proiectre.proiectre.entities.CompanyStatus;

import java.io.IOException;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_COMPANIES"}))

@WebServlet(name = "AddCompany", value = "/AddCompany")
public class AddCompany extends HttpServlet {
    @Inject
    private CompanyBean companyBean;
    @Inject
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserDto> users = userBean.findAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/addCompany.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        CompanyStatus status = CompanyStatus.valueOf(request.getParameter("status"));
        Long userId = Long.valueOf(request.getParameter("user_id"));

        companyBean.createCompany(name, description, status, userId);

        response.sendRedirect(request.getContextPath() + "/Companies");
    }
}