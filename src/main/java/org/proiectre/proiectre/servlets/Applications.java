package org.proiectre.proiectre.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.ejb.ApplicationBean;
import org.proiectre.proiectre.entities.ApplicationStatus;

import java.io.IOException;

@WebServlet(name="Applications", value="/Applications")
@DeclareRoles({"READ_APPLICATIONS", "WRITE_APPLICATIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_APPLICATIONS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_APPLICATIONS"})})
public class Applications extends HttpServlet {

    @Inject
    private ApplicationBean applicationBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("applications", applicationBean.findAllApplications());
        request.getRequestDispatcher("/WEB-INF/pages/applications.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        ApplicationStatus newStatus = ApplicationStatus.valueOf(request.getParameter("new_status"));
        applicationBean.updateStatus(id, newStatus);
        response.sendRedirect(request.getContextPath() + "/Applications");
    }
}