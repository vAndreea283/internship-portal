<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Adauga pozitie" activePage="myPositions">
  <h1>Adauga pozitie de internship</h1>
  <form class="mt-4" method="POST" action="${pageContext.request.contextPath}/AddMyPosition">
    <div class="mb-3">
      <label for="title" class="form-label">Titlu</label>
      <input type="text" class="form-control" id="title" name="title" required>
    </div>

    <div class="mb-3">
      <label for="description" class="form-label">Descriere</label>
      <textarea class="form-control" id="description" name="description" rows="3"></textarea>
    </div>

    <div class="mb-3">
      <label for="number_of_slots" class="form-label">Numar de locuri</label>
      <input type="number" min="1" class="form-control" id="number_of_slots" name="number_of_slots" required>
    </div>

    <div class="mb-3">
      <label for="year_of_study_target" class="form-label">An de studiu tinta</label>
      <input type="number" min="1" max="4" class="form-control" id="year_of_study_target" name="year_of_study_target" required>
    </div>

    <div class="mb-3">
      <label for="application_deadline" class="form-label">Termen limita aplicare</label>
      <input type="date" class="form-control" id="application_deadline" name="application_deadline" required>
    </div>

    <div class="mb-3">
      <label for="duration_weeks" class="form-label">Durata (saptamani)</label>
      <input type="number" min="1" class="form-control" id="duration_weeks" name="duration_weeks" required>
    </div>

    <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza (in asteptarea aprobarii)</button>
  </form>
</t:pageTemplate>