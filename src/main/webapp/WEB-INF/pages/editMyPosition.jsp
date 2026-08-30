<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Editeaza pozitie" activePage="myPositions">
    <h1>Editeaza pozitie de internship</h1>
    <form class="mt-4" method="POST" action="${pageContext.request.contextPath}/EditMyPosition">
        <input type="hidden" name="id" value="${position.id}">

        <div class="mb-3">
            <label for="title" class="form-label">Titlu</label>
            <input type="text" class="form-control" id="title" name="title" value="${position.title}" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Descriere</label>
            <textarea class="form-control" id="description" name="description" rows="3">${position.description}</textarea>
        </div>

        <div class="mb-3">
            <label for="number_of_slots" class="form-label">Numar de locuri</label>
            <input type="number" min="1" class="form-control" id="number_of_slots" name="number_of_slots" value="${position.numberOfSlots}" required>
        </div>

        <div class="mb-3">
            <label for="year_of_study_target" class="form-label">An de studiu tinta</label>
            <input type="number" min="1" max="4" class="form-control" id="year_of_study_target" name="year_of_study_target" value="${position.yearOfStudyTarget}" required>
        </div>

        <div class="mb-3">
            <label for="application_deadline" class="form-label">Termen limita aplicare</label>
            <input type="date" class="form-control" id="application_deadline" name="application_deadline" value="${position.applicationDeadline}" required>
        </div>

        <div class="mb-3">
            <label for="duration_weeks" class="form-label">Durata (saptamani)</label>
            <input type="number" min="1" class="form-control" id="duration_weeks" name="duration_weeks" value="${position.durationWeeks}" required>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza</button>
    </form>
</t:pageTemplate>