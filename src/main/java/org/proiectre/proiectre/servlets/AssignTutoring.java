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
import org.proiectre.proiectre.ejb.TutoringPositionBean;

import java.io.IOException;

@WebServlet(name = "AssignTutoring", value = "/AssignTutoring")
@DeclareRoles({"WRITE_TUTORING"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_TUTORING"}))
public class AssignTutoring extends HttpServlet {
    @Inject
    private TutoringPositionBean tutoringPositionBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long tutoringPositionId = Long.valueOf(request.getParameter("tutoring_position_id"));
        Long studentId = Long.valueOf(request.getParameter("student_id"));
        tutoringPositionBean.assignStudent(tutoringPositionId, studentId);
        response.sendRedirect(request.getContextPath() + "/TutoringPositions");
    }
}