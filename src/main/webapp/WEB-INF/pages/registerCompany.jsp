<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Inregistrare companie" activePage="register">
    <h1>Inregistreaza compania ta</h1>
    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">${error}</div>
    </c:if>
    <form class="mt-4" style="max-width: 500px;" method="POST"
          action="${pageContext.request.contextPath}/RegisterCompany">

        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
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
            <label for="company_name" class="form-label">Numele companiei</label>
            <input type="text" class="form-control" id="company_name" name="company_name" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Descriere</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Inregistreaza-te</button>
    </form>
</t:pageTemplate>