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

@WebServlet(name = "AddTutoringPosition", value = "/AddTutoringPosition")
@DeclareRoles({"WRITE_TUTORING"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_TUTORING"}))
public class AddTutoringPosition extends HttpServlet {
    @Inject
    private TutoringPositionBean tutoringPositionBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        tutoringPositionBean.createTutoringPosition(title, description);
        response.sendRedirect(request.getContextPath() + "/TutoringPositions");
    }
}