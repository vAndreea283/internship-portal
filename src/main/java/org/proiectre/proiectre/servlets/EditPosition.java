package org.proiectre.proiectre.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.common.CompanyDto;
import org.proiectre.proiectre.common.PositionDto;
import org.proiectre.proiectre.ejb.CompanyBean;
import org.proiectre.proiectre.ejb.PositionBean;
import org.proiectre.proiectre.entities.PositionStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "EditPosition", value = "/EditPosition")
public class EditPosition extends HttpServlet {
    @Inject
    private PositionBean positionBean;
    @Inject
    private CompanyBean companyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CompanyDto> companies = companyBean.findAllCompanies();
        request.setAttribute("companies", companies);

        Long id = Long.valueOf(request.getParameter("id"));
        PositionDto position = positionBean.findById(id);
        request.setAttribute("position", position);

        request.getRequestDispatcher("/WEB-INF/pages/editPosition.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Integer numberOfSlots = Integer.valueOf(request.getParameter("number_of_slots"));
        Integer yearOfStudyTarget = Integer.valueOf(request.getParameter("year_of_study_target"));
        LocalDate applicationDeadline = LocalDate.parse(request.getParameter("application_deadline"));
        Integer durationWeeks = Integer.valueOf(request.getParameter("duration_weeks"));
        PositionStatus status = PositionStatus.valueOf(request.getParameter("status"));
        Long companyId = Long.valueOf(request.getParameter("company_id"));

        positionBean.updatePosition(id, title, description, numberOfSlots, yearOfStudyTarget,
                applicationDeadline, durationWeeks, status, companyId);

        response.sendRedirect(request.getContextPath() + "/Positions");
    }
}