<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Editeaza pozitie" activePage="positions">
    <h1>Editeaza pozitie de internship</h1>
    <form class="needs-validation mt-4"
          novalidate
          method="POST"
          action="${pageContext.request.contextPath}/EditPosition">

        <input type="hidden" name="id" value="${position.id}">
        <div class="mb-3">
            <label for="title" class="form-label">Titlu</label>
            <input type="text" class="form-control" id="title" name="title" value="${position.title}" required>
            <div class="invalid-feedback">Titlul este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Descriere</label>
            <textarea class="form-control" id="description" name="description" rows="3">${position.description}</textarea>
        </div>

        <div class="mb-3">
            <label for="number_of_slots" class="form-label">Numar de locuri</label>
            <input type="number" min="1" class="form-control" id="number_of_slots" name="number_of_slots" value="${position.numberOfSlots}" required>
            <div class="invalid-feedback">Numarul de locuri este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="year_of_study_target" class="form-label">An de studiu tinta</label>
            <input type="number" min="2" max="3" class="form-control" id="year_of_study_target" name="year_of_study_target" value="${position.yearOfStudyTarget}" required>
            <div class="invalid-feedback">Anul de studiu este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="application_deadline" class="form-label">Termen limita aplicare</label>
            <input type="date" class="form-control" id="application_deadline" name="application_deadline" value="${position.applicationDeadline}" required>
            <div class="invalid-feedback">Termenul limita este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="duration_weeks" class="form-label">Durata (saptamani)</label>
            <input type="number" min="1" class="form-control" id="duration_weeks" name="duration_weeks" value="${position.durationWeeks}" required>
            <div class="invalid-feedback">Durata este obligatorie!</div>
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select class="form-select" id="status" name="status" required>
                <option value="PENDING" ${position.status eq 'PENDING' ? 'selected' : ''}>PENDING</option>
                <option value="APPROVED" ${position.status eq 'APPROVED' ? 'selected' : ''}>APPROVED</option>
                <option value="CLOSED" ${position.status eq 'CLOSED' ? 'selected' : ''}>CLOSED</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="company_id" class="form-label">Companie</label>
            <select class="form-select" id="company_id" name="company_id" required>
                <c:forEach var="company" items="${companies}">
                    <option value="${company.id}" ${company.id eq position.companyId ? 'selected' : ''}>${company.name}</option>
                </c:forEach>
            </select>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza</button>
    </form>
</t:pageTemplate>