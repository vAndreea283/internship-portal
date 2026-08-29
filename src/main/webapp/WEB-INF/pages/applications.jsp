<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Aplicatii" activePage="applications">
    <h1>Aplicatii primite</h1>
    <table class="table table-striped mt-4">
        <thead>
        <tr>
            <th>Student</th>
            <th>Pozitie</th>
            <th>Status</th>
            <c:if test="${pageContext.request.isUserInRole('WRITE_APPLICATIONS')}"><th></th></c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="app" items="${applications}">
            <tr>
                <td>${app.studentName}</td>
                <td>${app.positionTitle}</td>
                <td>${app.status}</td>
                <c:if test="${pageContext.request.isUserInRole('WRITE_APPLICATIONS')}">
                    <td>
                        <a class="btn btn-secondary btn-sm"
                           href="${pageContext.request.contextPath}/ApplicationDetails?id=${app.id}">Detalii</a>

                        <form method="POST" action="${pageContext.request.contextPath}/Applications" class="d-inline">
                            <input type="hidden" name="id" value="${app.id}">
                            <input type="hidden" name="new_status" value="ACCEPTED">
                            <button type="submit" class="btn btn-success btn-sm">Accepta</button>
                        </form>

                        <form method="POST" action="${pageContext.request.contextPath}/Applications" class="d-inline">
                            <input type="hidden" name="id" value="${app.id}">
                            <input type="hidden" name="new_status" value="REJECTED">
                            <button type="submit" class="btn btn-danger btn-sm">Respinge</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>
