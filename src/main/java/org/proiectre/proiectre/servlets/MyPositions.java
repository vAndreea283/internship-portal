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

@WebServlet(name="MyPositions", value="/MyPositions")
@DeclareRoles({"MANAGE_OWN_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"MANAGE_OWN_POSITIONS"}))
public class MyPositions extends HttpServlet {

    @Inject
    private PositionBean positionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("positions", positionBean.findByCompanyUsername(request.getRemoteUser()));
        request.getRequestDispatcher("/WEB-INF/pages/myPositions.jsp").forward(request, response);
    }
}