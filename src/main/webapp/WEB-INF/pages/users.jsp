<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Useri" activePage="users">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Conturi</h1>
        <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
            <a href="${pageContext.request.contextPath}/AddUser" class="btn btn-primary btn-lg">Adauga cont</a>
        </c:if>
    </div>

    <table class="table table-striped mt-4">
        <thead>
        <tr><th>Username</th><th>Email</th></tr>
        </thead>

        <tbody>
        <c:forEach var="user" items="${users}">
            <tr><td>${user.username}</td><td>${user.email}</td></tr>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>