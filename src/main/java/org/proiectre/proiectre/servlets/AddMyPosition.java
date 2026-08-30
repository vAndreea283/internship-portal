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
import org.proiectre.proiectre.common.CompanyDto;
import org.proiectre.proiectre.ejb.CompanyBean;
import org.proiectre.proiectre.ejb.PositionBean;
import org.proiectre.proiectre.entities.PositionStatus;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name="AddMyPosition", value="/AddMyPosition")
@DeclareRoles({"MANAGE_OWN_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"MANAGE_OWN_POSITIONS"}))
public class AddMyPosition extends HttpServlet {

    @Inject
    private PositionBean positionBean;

    @Inject
    private CompanyBean companyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/addMyPosition.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CompanyDto company = companyBean.findByUsername(request.getRemoteUser());

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Integer numberOfSlots = Integer.valueOf(request.getParameter("number_of_slots"));
        Integer yearOfStudyTarget = Integer.valueOf(request.getParameter("year_of_study_target"));
        LocalDate applicationDeadline = LocalDate.parse(request.getParameter("application_deadline"));
        Integer durationWeeks = Integer.valueOf(request.getParameter("duration_weeks"));

        // pozitia noua porneste mereu PENDING - o aproba departamentul
        positionBean.createPosition(title, description, numberOfSlots, yearOfStudyTarget,
                applicationDeadline, durationWeeks, PositionStatus.valueOf("PENDING"), company.getId());

        response.sendRedirect(request.getContextPath() + "/MyPositions");
    }
}