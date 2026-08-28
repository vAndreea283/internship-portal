package org.proiectre.proiectre.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.common.StudentDto;
import org.proiectre.proiectre.ejb.StudentBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DeclareRoles({"READ_STUDENTS", "WRITE_STUDENTS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_STUDENTS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_STUDENTS"})})

@WebServlet(name = "Students", value = "/Students")
public class Students extends HttpServlet {
    @Inject
    private StudentBean studentBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<StudentDto> students = studentBean.findAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/pages/students.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] idsParam = request.getParameterValues("student_ids");
        if (idsParam != null) {
            List<Long> ids = new ArrayList<>();
            for (String s : idsParam) {
                ids.add(Long.valueOf(s));
            }
            studentBean.deleteStudentsByIds(ids);
        }
        response.sendRedirect(request.getContextPath() + "/Students");
    }
}