<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Adauga cont" activePage="users">
    <h1>Adauga cont</h1>
    <form class="needs-validation mt-4"
          novalidate
          method="POST"
          action="${pageContext.request.contextPath}/AddUser">

        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" required>
            <div class="invalid-feedback">Username-ul este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
            <div class="invalid-feedback">Email-ul este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Parola</label>
            <input type="password" class="form-control" id="password" name="password" required>
            <div class="invalid-feedback">Parola este obligatorie!</div>
        </div>

        <div class="mb-3">
            <label class="form-label">Roluri</label>
            <c:forEach var="group" items="${userGroups}">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="user_groups" value="${group}" id="ug_${group}">
                    <label class="form-check-label" for="ug_${group}">${group}</label>
                </div>
            </c:forEach>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza</button>
    </form>
</t:pageTemplate>