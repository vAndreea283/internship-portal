<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Companii" activePage="companies">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Companii inregistrate</h1>
        <a href="${pageContext.request.contextPath}/AddCompany" class="btn btn-primary btn-lg">Adauga companie</a>
    </div>

    <form method="POST" action="${pageContext.request.contextPath}/Companies">
        <button type="submit" class="btn btn-danger mt-3">Sterge selectate</button>
        <table class="table table-striped mt-3">
            <thead>
            <tr>
                <th></th>
                <th>Nume</th>
                <th>Descriere</th>
                <th>Status</th>
                <th>Cont</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="company" items="${companies}">
                <tr>
                    <td><input type="checkbox" name="company_ids" value="${company.id}"></td>
                    <td>${company.name}</td>
                    <td>${company.description}</td>
                    <td>${company.status}</td>
                    <td>${company.username}</td>
                    <td><a class="btn btn-secondary btn-sm"
                           href="${pageContext.request.contextPath}/EditCompany?id=${company.id}">Editeaza</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:pageTemplate>