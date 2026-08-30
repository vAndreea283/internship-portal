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

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteApplication", value = "/DeleteApplication")
@DeclareRoles({"WRITE_APPLICATIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_APPLICATIONS"}))
public class DeleteApplication extends HttpServlet {
    @Inject
    private ApplicationBean applicationBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        applicationBean.deleteApplicationsByIds(List.of(id));
        response.sendRedirect(request.getContextPath() + "/Applications");
    }
}