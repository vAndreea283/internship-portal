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
import org.proiectre.proiectre.common.StudentCvDto;
import org.proiectre.proiectre.ejb.StudentBean;

import java.io.IOException;

@WebServlet(name = "StudentCvs", value = "/StudentCvs")
@DeclareRoles({"READ_STUDENTS", "APPLY_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_STUDENTS", "APPLY_POSITIONS"}))
public class StudentCvs extends HttpServlet {
    @Inject
    private StudentBean studentBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long studentId = Long.valueOf(request.getParameter("id"));
        StudentCvDto cv = studentBean.findCvByStudentId(studentId);
        if (cv == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setContentType(cv.getFileType());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + cv.getFilename() + "\""); // forteaza descarcarea
        response.getOutputStream().write(cv.getFileContent());
    }
}