<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Aplicatii" activePage="applications">
    <h1>Aplicatii primite (toate companiile)</h1>

    <a href="${pageContext.request.contextPath}/ExportGrades" class="btn btn-outline-secondary mt-2">Exporta note (CSV)</a>

    <c:if test="${not empty sessionScope.importMessage}">
        <div class="alert alert-info mt-3">${sessionScope.importMessage}</div>
        <c:remove var="importMessage" scope="session"/>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('WRITE_APPLICATIONS')}">
        <form method="POST" action="${pageContext.request.contextPath}/ImportGrades"
              enctype="multipart/form-data" class="d-flex align-items-center gap-2 mt-3">
            <input type="file" name="file" accept=".csv" class="form-control" style="max-width:300px;" required>
            <button type="submit" class="btn btn-outline-primary">Importa note (CSV)</button>
            <span class="text-muted small">Format: studentUsername,positionTitle,value</span>
        </form>
    </c:if>

    <table class="table table-striped mt-4">
        <thead>
        <tr>
            <th>Student</th>
            <th>Pozitie</th>
            <th>Status</th>
            <th>Rezultat interviu</th>
            <th>Nota</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="app" items="${applications}">
            <tr>
                <td>${app.studentName}</td>
                <td>${app.positionTitle}</td>
                <td>${app.status}</td>
                <td>${empty app.interviewResult ? '-' : app.interviewResult}</td>
                <td>${empty app.gradeValue ? '-' : app.gradeValue}</td>
                <td>
                    <a class="btn btn-secondary btn-sm"
                       href="${pageContext.request.contextPath}/ApplicationDetails?id=${app.id}">Detalii</a>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_APPLICATIONS')}">
                        <form method="POST" action="${pageContext.request.contextPath}/DeleteApplication" class="d-inline">
                            <input type="hidden" name="id" value="${app.id}">
                            <button type="submit" class="btn btn-outline-danger btn-sm"
                                    onclick="return confirm('Sigur stergi aceasta aplicatie?');">Sterge</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>
