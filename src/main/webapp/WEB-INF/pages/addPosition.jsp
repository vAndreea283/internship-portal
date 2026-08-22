<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Adauga pozitie" activePage="positions">
  <h1>Adauga pozitie de internship</h1>
  <form class="needs-validation mt-4" <%--in interiorul acestui element avem date care pot fi trimise catre server--%>
        novalidate <%--nu folosi validarea HTML5 implicita a browserului pentru acest formular--%>
        method="POST"
        action="${pageContext.request.contextPath}/AddPosition">

    <div class="mb-3">
      <label for="title" class="form-label">Titlu</label>
      <input type="text" class="form-control" id="title" name="title" required>
      <div class="invalid-feedback">Titlul este obligatoriu!</div>
    </div>

    <div class="mb-3">
      <label for="description" class="form-label">Descriere</label>
      <textarea class="form-control" id="description" name="description" rows="3"></textarea>
    </div>

    <div class="mb-3">
      <label for="number_of_slots" class="form-label">Numar de locuri</label>
      <input type="number" min="1" class="form-control" id="number_of_slots" name="number_of_slots" required>
      <div class="invalid-feedback">Numarul de locuri este obligatoriu!</div>
    </div>

    <div class="mb-3">
      <label for="year_of_study_target" class="form-label">An de studiu tinta</label>
      <input type="number" min="2" max="3" class="form-control" id="year_of_study_target" name="year_of_study_target" required>
      <div class="invalid-feedback">Anul de studiu este obligatoriu!</div>
    </div>

    <div class="mb-3">
      <label for="application_deadline" class="form-label">Termen limită aplicare</label>
      <input type="date" class="form-control" id="application_deadline" name="application_deadline" required>
      <div class="invalid-feedback">Termenul limita este obligatoriu!</div>
    </div>

    <div class="mb-3">
      <label for="duration_weeks" class="form-label">Durata (saptamani)</label>
      <input type="number" min="1" class="form-control" id="duration_weeks" name="duration_weeks" required>
      <div class="invalid-feedback">Durata este obligatorie!</div>
    </div>

    <div class="mb-3">
      <label for="status" class="form-label">Status</label>
      <select class="form-select" id="status" name="status" required>
        <option value="PENDING">PENDING</option>
        <option value="APPROVED">APPROVED</option>
        <option value="CLOSED">CLOSED</option>
      </select>
    </div>

    <div class="mb-3">
      <label for="company_id" class="form-label">Companie</label>
      <select class="form-select" id="company_id" name="company_id" required>
        <option value="">Alege...</option>
        <c:forEach var="company" items="${companies}">
          <option value="${company.id}">${company.name}</option>
        </c:forEach>
      </select>
      <div class="invalid-feedback">Trebuie sa alegi o companie!</div>
    </div>

    <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza</button>
  </form>
</t:pageTemplate>