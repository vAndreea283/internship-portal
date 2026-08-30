package org.proiectre.proiectre.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.ejb.CompanyBean;

import java.io.IOException;

@WebServlet(name="MyCompany", value="/MyCompany")
@DeclareRoles({"MANAGE_OWN_COMPANY"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"MANAGE_OWN_COMPANY"}))
public class MyCompany extends HttpServlet {

    @Inject
    private CompanyBean companyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("company", companyBean.findByUsername(request.getRemoteUser()));
        request.getRequestDispatcher("/WEB-INF/pages/myCompany.jsp").forward(request, response);
    }
}