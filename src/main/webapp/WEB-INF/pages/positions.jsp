<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Pozitii" activePage="positions">
  <div class="d-flex justify-content-between align-items-center">
    <h1>Pozitii de internship disponibile</h1>
    <a href="${pageContext.request.contextPath}/AddPosition" class="btn btn-primary btn-lg">Adauga pozitie</a>
  </div>

  <form method="POST" action="${pageContext.request.contextPath}/Positions">
    <button type="submit" class="btn btn-danger mt-3">Sterge selectate</button>
    <table class="table table-striped mt-3">
      <thead>
      <tr>
        <th></th>
        <th>Titlu</th>
        <th>Companie</th>
        <th>An de studiu</th>
        <th>Locuri</th>
        <th>Termen limita</th>
        <th>Status</th>
        <th></th>
      </tr>
      </thead>

      <tbody>
      <c:forEach var="position" items="${positions}">
        <tr>
          <td><input type="checkbox" name="position_ids" value="${position.id}"></td>
          <td>${position.title}</td>
          <td>${position.companyName}</td>
          <td>${position.yearOfStudyTarget}</td>
          <td>${position.numberOfSlots}</td>
          <td>${position.applicationDeadline}</td>
          <td>${position.status}</td>
          <td><a class="btn btn-secondary btn-sm"
                 href="${pageContext.request.contextPath}/EditPosition?id=${position.id}">Editeaza</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </form>
</t:pageTemplate>