<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Pozitiile mele" activePage="myPositions">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Pozitiile mele</h1>
        <a href="${pageContext.request.contextPath}/AddMyPosition" class="btn btn-primary btn-lg">Adauga pozitie</a>
    </div>
    <table class="table table-striped mt-4">
        <thead>
        <tr><th>Titlu</th><th>Status</th><th>Locuri</th><th>Termen limita</th><th></th></tr>
        </thead>

        <tbody>
        <c:forEach var="position" items="${positions}">
            <tr>
                <td>${position.title}</td>
                <td>${position.status}</td>
                <td>${position.numberOfSlots}</td>
                <td>${position.applicationDeadline}</td>
                <td><a class="btn btn-secondary btn-sm"
                       href="${pageContext.request.contextPath}/EditMyPosition?id=${position.id}">Editeaza</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>