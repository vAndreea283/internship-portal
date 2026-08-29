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
import org.proiectre.proiectre.ejb.PositionBean;

import java.io.IOException;

@WebServlet(name = "Stats", value = "/Stats")
@DeclareRoles({"WRITE_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_POSITIONS"}))
public class Stats extends HttpServlet {
    @Inject
    private PositionBean positionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("countsByYear", positionBean.countPositionsByYear());
        request.getRequestDispatcher("/WEB-INF/pages/stats.jsp").forward(request, response);
    }
}