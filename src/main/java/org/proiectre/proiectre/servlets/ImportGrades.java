package org.proiectre.proiectre.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.proiectre.proiectre.ejb.GradeBean;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "ImportGrades", value = "/ImportGrades")
@MultipartConfig
@DeclareRoles({"WRITE_APPLICATIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_APPLICATIONS"}))
public class ImportGrades extends HttpServlet {
    @Inject
    private GradeBean gradeBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String message;
        try (InputStream inputStream = filePart.getInputStream()) {
            byte[] content = inputStream.readAllBytes();
            message = gradeBean.importGradesFromCsv(content);
        }
        request.getSession().setAttribute("importMessage", message);
        response.sendRedirect(request.getContextPath() + "/Applications");
    }
}