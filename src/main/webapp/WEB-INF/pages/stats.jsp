<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Statistici" activePage="stats">
    <h1>Pozitii oferite pe an de studiu</h1>
    <table class="table table-striped mt-4" style="max-width: 400px;">
        <thead><tr><th>An de studiu</th><th>Numar pozitii</th></tr></thead>
        <tbody>
        <c:forEach var="entry" items="${countsByYear}">
            <tr><td>${entry.key}</td><td>${entry.value}</td></tr>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>