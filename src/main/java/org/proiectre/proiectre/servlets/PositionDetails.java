package org.proiectre.proiectre.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proiectre.proiectre.ejb.PositionBean;

import java.io.IOException;

@WebServlet(name = "PositionDetails", value = "/PositionDetails")
public class PositionDetails extends HttpServlet {
    @Inject
    private PositionBean positionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        request.setAttribute("position", positionBean.findById(id));
        request.getRequestDispatcher("/WEB-INF/pages/positionDetails.jsp").forward(request, response);
    }
}