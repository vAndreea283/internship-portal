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
import org.proiectre.proiectre.ejb.PositionBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DeclareRoles({"READ_POSITIONS", "WRITE_POSITIONS", "APPLY_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_POSITIONS", "APPLY_POSITIONS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_POSITIONS"})})

@WebServlet(name = "Positions", value = "/Positions")
public class Positions extends HttpServlet {
    @Inject
    private PositionBean positionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        String q = request.getParameter("q");
        boolean canSeeAll = request.isUserInRole("WRITE_POSITIONS"); // departamentul vede tot, ca sa poata modera

        if (q != null && !q.isBlank()) {
            if (canSeeAll) {
                request.setAttribute("positions", positionBean.searchPositionsPaged(q, page));
                request.setAttribute("totalPages", positionBean.countSearchResults(q));
            } else {
                request.setAttribute("positions", positionBean.searchVisiblePositionsPaged(q, page));
                request.setAttribute("totalPages", positionBean.countVisibleSearchResults(q));
            }
            request.setAttribute("searchQuery", q);
        } else {
            if (canSeeAll) {
                request.setAttribute("positions", positionBean.findAllPositionsPaged(page));
                request.setAttribute("totalPages", positionBean.countAllPositions());
            } else {
                request.setAttribute("positions", positionBean.findVisiblePositionsPaged(page));
                request.setAttribute("totalPages", positionBean.countVisiblePositions());
            }
        }
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/WEB-INF/pages/positions.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] idsParam = request.getParameterValues("position_ids");
        if (idsParam != null) {
            List<Long> ids = new ArrayList<>();
            for (String s : idsParam) {
                ids.add(Long.valueOf(s));
            }
            positionBean.deletePositionsByIds(ids);
        }
        response.sendRedirect(request.getContextPath() + "/Positions");
    }
}