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
import org.proiectre.proiectre.entities.ApplicationStatus;

import java.io.IOException;

@WebServlet(name = "UpdateApplicationStatus", value = "/UpdateApplicationStatus")
@DeclareRoles({"MANAGE_OWN_APPLICATIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"MANAGE_OWN_APPLICATIONS"}))
public class UpdateApplicationStatus extends HttpServlet {
    @Inject
    private ApplicationBean applicationBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));

        if (!applicationBean.isOwnedByCompany(id, request.getRemoteUser())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        ApplicationStatus newStatus = ApplicationStatus.valueOf(request.getParameter("new_status"));
        applicationBean.updateStatus(id, newStatus);

        response.sendRedirect(request.getContextPath() + "/MyApplicationsReceived");
    }
}
