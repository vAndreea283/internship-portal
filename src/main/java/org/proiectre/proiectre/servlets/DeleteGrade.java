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
import org.proiectre.proiectre.ejb.GradeBean;

import java.io.IOException;

@WebServlet(name = "DeleteGrade", value = "/DeleteGrade")
@DeclareRoles({"WRITE_APPLICATIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_APPLICATIONS"}))
public class DeleteGrade extends HttpServlet {
    @Inject
    private GradeBean gradeBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long applicationId = Long.valueOf(request.getParameter("application_id"));
        gradeBean.deleteByApplicationId(applicationId);
        response.sendRedirect(request.getContextPath() + "/ApplicationDetails?id=" + applicationId);
    }
}