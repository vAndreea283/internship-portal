<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Editeaza student" activePage="students">
    <h1>Editeaza student</h1>
    <form class="needs-validation mt-4"
          novalidate
          method="POST"
          action="${pageContext.request.contextPath}/EditStudent">

        <input type="hidden" name="id" value="${student.id}">
        <div class="mb-3">
            <label for="full_name" class="form-label">Nume complet</label>
            <input type="text" class="form-control" id="full_name" name="full_name" value="${student.fullName}" required>
            <div class="invalid-feedback">Numele este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="year_of_study" class="form-label">An de studiu</label>
            <input type="number" min="1" max="4" class="form-control" id="year_of_study" name="year_of_study" value="${student.yearOfStudy}" required>
            <div class="invalid-feedback">Anul de studiu este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="user_id" class="form-label">Cont asociat</label>
            <select class="form-select" id="user_id" name="user_id" required>
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}" ${user.id eq student.userId ? 'selected' : ''}>${user.username}</option>
                </c:forEach>
            </select>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza</button>
    </form>
</t:pageTemplate>