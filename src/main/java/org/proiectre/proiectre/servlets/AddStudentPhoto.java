package org.proiectre.proiectre.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.proiectre.proiectre.common.StudentDto;
import org.proiectre.proiectre.ejb.StudentBean;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "AddStudentPhoto", value = "/AddStudentPhoto")
@MultipartConfig
@DeclareRoles({"APPLY_POSITIONS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"APPLY_POSITIONS"}))
public class AddStudentPhoto extends HttpServlet {
    @Inject
    private StudentBean studentBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDto student = studentBean.findByUsername(request.getRemoteUser());
        Part filePart = request.getPart("photo");

        String filename = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();

        try (InputStream inputStream = filePart.getInputStream()) {
            byte[] fileContent = inputStream.readAllBytes();
            studentBean.addPhotoToStudent(student.getId(), filename, fileType, fileContent);
        }

        response.sendRedirect(request.getContextPath() + "/MyProfile");
    }
}