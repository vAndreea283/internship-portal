<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Profilul meu" activePage="myProfile">
    <h1>Profilul meu</h1>
    <p><strong>${student.fullName}</strong> — An ${student.yearOfStudy}</p>

    <div class="row mt-4">
        <div class="col-md-4">
            <h4>Poza</h4>

            <img src="${pageContext.request.contextPath}/StudentPhotos?id=${student.id}"
                 class="img-thumbnail mb-2" style="max-width: 200px;"
                 onerror="this.style.display='none'">

            <form method="POST" action="${pageContext.request.contextPath}/AddStudentPhoto"
                  enctype="multipart/form-data">
                <input type="file" name="photo" accept="image/*" class="form-control mb-2" required>
                <button type="submit" class="btn btn-primary btn-sm">Incarca poza</button>
            </form>
        </div>

        <div class="col-md-4">
            <h4>CV</h4>

            <p><a href="${pageContext.request.contextPath}/StudentCvs?id=${student.id}">Descarca CV curent</a></p>

            <form method="POST" action="${pageContext.request.contextPath}/AddStudentCv"
                  enctype="multipart/form-data">
                <input type="file" name="cv" accept=".pdf,.doc,.docx" class="form-control mb-2" required>
                <button type="submit" class="btn btn-primary btn-sm">Incarca CV</button>
            </form>
        </div>
    </div>
</t:pageTemplate>