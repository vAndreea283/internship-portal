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
import org.proiectre.proiectre.common.StudentDto;
import org.proiectre.proiectre.ejb.ApplicationBean;
import org.proiectre.proiectre.ejb.StudentBean;

import java.io.IOException;

@WebServlet(name = "Apply", value = "/Apply")
@DeclareRoles({"APPLY_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"APPLY_POSITIONS"}))
public class Apply extends HttpServlet {
    @Inject
    private ApplicationBean applicationBean;
    @Inject
    private StudentBean studentBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long positionId = Long.valueOf(request.getParameter("position_id"));
        StudentDto student = studentBean.findByUsername(request.getRemoteUser());

        if (student == null) {
            response.sendRedirect(request.getContextPath() + "/Positions");
            return;
        }

        String error = applicationBean.createApplication(student.getId(), positionId);
        if (error != null) {
            request.getSession().setAttribute("applyError", error);
        }
        response.sendRedirect(request.getContextPath() + "/Positions");
    }
}