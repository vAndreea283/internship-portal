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

@WebServlet(name = "ExportGrades", value = "/ExportGrades")
@DeclareRoles({"READ_APPLICATIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_APPLICATIONS"}))
public class ExportGrades extends HttpServlet {
    @Inject
    private GradeBean gradeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"note.csv\"");
        response.getWriter().write(gradeBean.exportGradesAsCsv());
    }
}