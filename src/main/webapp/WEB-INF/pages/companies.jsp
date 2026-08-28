<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Companii" activePage="companies">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Companii inregistrate</h1>
        <c:if test="${pageContext.request.isUserInRole('WRITE_COMPANIES')}">
            <a href="${pageContext.request.contextPath}/AddCompany" class="btn btn-primary btn-lg">Adauga companie</a>
        </c:if>
    </div>

    <form method="POST" action="${pageContext.request.contextPath}/Companies">
        <c:if test="${pageContext.request.isUserInRole('WRITE_COMPANIES')}">
            <button type="submit" class="btn btn-danger mt-3">Sterge selectate</button>
        </c:if>
        <table class="table table-striped mt-3">
            <thead>
            <tr>
                <c:if test="${pageContext.request.isUserInRole('WRITE_COMPANIES')}"><th></th></c:if>
                <th>Nume</th>
                <th>Descriere</th>
                <th>Status</th>
                <th>Cont</th>
                <c:if test="${pageContext.request.isUserInRole('WRITE_COMPANIES')}"><th></th></c:if>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="company" items="${companies}">
                <tr>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_COMPANIES')}">
                        <td><input type="checkbox" name="company_ids" value="${company.id}"></td>
                    </c:if>
                    <td>${company.name}</td>
                    <td>${company.description}</td>
                    <td>${company.status}</td>
                    <td>${company.username}</td>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_COMPANIES')}">
                        <td><input type="checkbox" name="company_ids" value="${company.id}"></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:pageTemplate>