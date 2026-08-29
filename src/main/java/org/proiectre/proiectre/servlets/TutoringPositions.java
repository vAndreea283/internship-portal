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

@WebServlet(name = "TutoringPositions", value = "/TutoringPositions")
@DeclareRoles({"READ_TUTORING", "WRITE_TUTORING"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_TUTORING"}))
public class TutoringPositions extends HttpServlet {
    @Inject
    private TutoringPositionBean tutoringPositionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("tutoringPositions", tutoringPositionBean.findAllTutoringPositions());
        request.setAttribute("unassignedStudents", tutoringPositionBean.findStudentsWithoutInternship());
        request.getRequestDispatcher("/WEB-INF/pages/tutoringPositions.jsp").forward(request, response);
    }
}