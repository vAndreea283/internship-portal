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
import org.proiectre.proiectre.ejb.InterviewBean;

import java.io.IOException;

@WebServlet(name = "DeleteInterview", value = "/DeleteInterview")
@DeclareRoles({"MANAGE_OWN_APPLICATIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"MANAGE_OWN_APPLICATIONS"}))
public class DeleteInterview extends HttpServlet {
    @Inject
    private InterviewBean interviewBean;
    @Inject
    private ApplicationBean applicationBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long applicationId = Long.valueOf(request.getParameter("application_id"));

        if (!applicationBean.isOwnedByCompany(applicationId, request.getRemoteUser())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        interviewBean.deleteByApplicationId(applicationId);
        response.sendRedirect(request.getContextPath() + "/ApplicationDetails?id=" + applicationId);
    }
}