package org.proiectre.proiectre.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.common.UserDto;
import org.proiectre.proiectre.ejb.StudentBean;
import org.proiectre.proiectre.ejb.UserBean;

import java.io.IOException;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_STUDENTS"}))

@WebServlet(name = "AddStudent", value = "/AddStudent")
public class AddStudent extends HttpServlet {
    @Inject
    private StudentBean studentBean;
    @Inject
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserDto> users = userBean.findAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/addStudent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("full_name");
        Integer yearOfStudy = Integer.valueOf(request.getParameter("year_of_study"));
        String cvPath = request.getParameter("cv_path");
        String imagePath = request.getParameter("image_path");
        Long userId = Long.valueOf(request.getParameter("user_id"));

        studentBean.createStudent(fullName, yearOfStudy, userId);

        response.sendRedirect(request.getContextPath() + "/Students");
    }
}