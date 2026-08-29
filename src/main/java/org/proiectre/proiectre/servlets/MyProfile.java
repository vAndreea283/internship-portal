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
import org.proiectre.proiectre.ejb.StudentBean;

import java.io.IOException;

@WebServlet(name = "MyProfile", value = "/MyProfile")
@DeclareRoles({"APPLY_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"APPLY_POSITIONS"}))
public class MyProfile extends HttpServlet {
    @Inject
    private StudentBean studentBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("student", studentBean.findByUsername(request.getRemoteUser()));
        request.getRequestDispatcher("/WEB-INF/pages/myProfile.jsp").forward(request, response);
    }
}