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
import org.proiectre.proiectre.ejb.GradeBean;
import org.proiectre.proiectre.ejb.InterviewBean;

import java.io.IOException;

@WebServlet(name = "ApplicationDetails", value = "/ApplicationDetails")
@DeclareRoles({"WRITE_APPLICATIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_APPLICATIONS"}))
public class ApplicationDetails extends HttpServlet {
    @Inject
    private ApplicationBean applicationBean;
    @Inject
    private InterviewBean interviewBean;
    @Inject
    private GradeBean gradeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        request.setAttribute("application", applicationBean.findById(id));
        request.setAttribute("interview", interviewBean.findByApplicationId(id));
        request.setAttribute("grade", gradeBean.findByApplicationId(id));
        request.getRequestDispatcher("/WEB-INF/pages/applicationDetails.jsp").forward(request, response);
    }
}