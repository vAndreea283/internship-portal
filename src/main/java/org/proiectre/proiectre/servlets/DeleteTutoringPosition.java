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
import java.util.List;

@WebServlet(name = "DeleteTutoringPosition", value = "/DeleteTutoringPosition")
@DeclareRoles({"WRITE_TUTORING"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_TUTORING"}))
public class DeleteTutoringPosition extends HttpServlet {
    @Inject
    private TutoringPositionBean tutoringPositionBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        tutoringPositionBean.deleteTutoringPositionsByIds(List.of(id));
        response.sendRedirect(request.getContextPath() + "/TutoringPositions");
    }
}