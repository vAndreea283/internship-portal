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
import org.proiectre.proiectre.common.CompanyDto;
import org.proiectre.proiectre.ejb.CompanyBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DeclareRoles({"READ_COMPANIES", "WRITE_COMPANIES"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_COMPANIES"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_COMPANIES"})})

@WebServlet(name = "Companies", value = "/Companies")
public class Companies extends HttpServlet {
    @Inject
    private CompanyBean companyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CompanyDto> companies = companyBean.findAllCompanies();
        request.setAttribute("companies", companies);
        request.getRequestDispatcher("/WEB-INF/pages/companies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] idsParam = request.getParameterValues("company_ids");
        if (idsParam != null) {
            List<Long> ids = new ArrayList<>();
            for (String s : idsParam) {
                ids.add(Long.valueOf(s));
            }
            companyBean.deleteCompaniesByIds(ids);
        }
        response.sendRedirect(request.getContextPath() + "/Companies");
    }
}