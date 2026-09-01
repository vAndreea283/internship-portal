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
import org.proiectre.proiectre.ejb.ApplicationBean;

import java.io.IOException;

@WebServlet(name="MyApplications", value="/MyApplications")
@DeclareRoles({"APPLY_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"APPLY_POSITIONS"}))
public class MyApplications extends HttpServlet {
    @Inject
    private ApplicationBean applicationBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("applications", applicationBean.findByStudentUsername(request.getRemoteUser()));
        request.getRequestDispatcher("/WEB-INF/pages/myApplications.jsp").forward(request, response);
    }
}