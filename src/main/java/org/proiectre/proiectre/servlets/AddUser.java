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
import org.proiectre.proiectre.ejb.UserBean;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "AddUser", value = "/AddUser")
@DeclareRoles({"READ_USERS", "WRITE_USERS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
public class AddUser extends HttpServlet {

    @Inject
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("userGroups", new String[]{
                "READ_POSITIONS", "WRITE_POSITIONS",
                "READ_COMPANIES", "WRITE_COMPANIES",
                "READ_STUDENTS", "WRITE_STUDENTS",
                "READ_USERS", "WRITE_USERS"});
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String[] userGroups = request.getParameterValues("user_groups");
        if (userGroups == null) {
            userGroups = new String[0];
        }
        userBean.createUser(username, email, password, Arrays.asList(userGroups));
        response.sendRedirect(request.getContextPath() + "/Users");
    }
}