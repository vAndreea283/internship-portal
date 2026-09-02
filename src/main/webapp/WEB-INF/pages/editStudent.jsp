<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Editeaza student" activePage="students">
    <h1>Editeaza student</h1>
    <form class="mt-4" method="POST" action="${pageContext.request.contextPath}/EditStudent">
        <input type="hidden" name="id" value="${student.id}">
        <div class="mb-3">
            <label for="full_name" class="form-label">Nume complet</label>
            <input type="text" class="form-control" id="full_name" name="full_name" value="${student.fullName}" required>
        </div>

        <div class="mb-3">
            <label for="year_of_study" class="form-label">An de studiu</label>
            <input type="number" min="1" max="4" class="form-control" id="year_of_study" name="year_of_study" value="${student.yearOfStudy}" required>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza</button>
    </form>
</t:pageTemplate>