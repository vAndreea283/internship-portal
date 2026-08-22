package org.proiectre.proiectre.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.common.PositionDto;
import org.proiectre.proiectre.ejb.PositionBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Positions", value = "/Positions")
public class Positions extends HttpServlet {
    @Inject
    private PositionBean positionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PositionDto> positions = positionBean.findAllPositions();
        request.setAttribute("positions", positions);
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