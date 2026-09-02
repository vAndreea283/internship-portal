<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Adauga student" activePage="students">
    <h1>Adauga student</h1>
    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">${error}</div>
    </c:if>
    <form class="mt-4" method="POST" action="${pageContext.request.contextPath}/AddStudent">
        <div class="mb-3">
            <label for="username" class="form-label">Username cont</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Parola</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <div class="mb-3">
            <label for="full_name" class="form-label">Nume complet</label>
            <input type="text" class="form-control" id="full_name" name="full_name" required>
        </div>

        <div class="mb-3">
            <label for="year_of_study" class="form-label">An de studiu</label>
            <input type="number" min="1" max="4" class="form-control" id="year_of_study" name="year_of_study" required>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza</button>
    </form>
</t:pageTemplate>