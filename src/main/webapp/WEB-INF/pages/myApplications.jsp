<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Aplicatiile mele" activePage="myApplications">
    <h1>Aplicatiile mele</h1>
    <table class="table table-striped mt-4">
        <thead>
        <tr><th>Pozitie</th><th>Status</th></tr>
        </thead>

        <tbody>
        <c:forEach var="app" items="${applications}">
            <tr><td>${app.positionTitle}</td><td>${app.status}</td></tr>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>