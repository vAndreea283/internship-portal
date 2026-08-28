package org.proiectre.proiectre.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest; // cererea venita de la browser catre server
import jakarta.servlet.http.HttpServletResponse; // raspunsul serverului catre browser
import org.proiectre.proiectre.common.CompanyDto;
import org.proiectre.proiectre.ejb.CompanyBean;
import org.proiectre.proiectre.ejb.PositionBean;
import org.proiectre.proiectre.entities.PositionStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_POSITIONS"}))

@WebServlet(name = "AddPosition", value = "/AddPosition")
public class AddPosition extends HttpServlet {
    @Inject
    private PositionBean positionBean;
    @Inject
    private CompanyBean companyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // apelata cand browserul face o cerere HTTP
        List<CompanyDto> companies = companyBean.findAllCompanies();
        request.setAttribute("companies", companies);
        request.getRequestDispatcher("/WEB-INF/pages/addPosition.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Integer numberOfSlots = Integer.valueOf(request.getParameter("number_of_slots"));
        Integer yearOfStudyTarget = Integer.valueOf(request.getParameter("year_of_study_target"));
        LocalDate applicationDeadline = LocalDate.parse(request.getParameter("application_deadline"));
        Integer durationWeeks = Integer.valueOf(request.getParameter("duration_weeks"));
        PositionStatus status = PositionStatus.valueOf(request.getParameter("status")); // conversie string-enum: PositionStatus.valueOf(...) cauta constanta cu numele exact primit din formular
        Long companyId = Long.valueOf(request.getParameter("company_id"));

        positionBean.createPosition(title, description, numberOfSlots, yearOfStudyTarget,
                applicationDeadline, durationWeeks, status, companyId);

        response.sendRedirect(request.getContextPath() + "/Positions");
    }
}