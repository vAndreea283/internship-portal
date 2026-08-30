<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Detalii aplicatie" activePage="applications">
    <h1>${application.studentName} — ${application.positionTitle}</h1>
    <p>Status curent: <strong>${application.status}</strong></p>

    <hr>
    <h3>Interviu</h3>
    <form class="mt-3" method="POST" action="${pageContext.request.contextPath}/SaveInterview">
        <input type="hidden" name="application_id" value="${application.id}">

        <div class="mb-3">
            <label for="summary" class="form-label">Rezumat interviu</label>
            <textarea class="form-control" id="summary" name="summary" rows="4">${interview.summary}</textarea>
        </div>

        <div class="mb-3">
            <label for="result" class="form-label">Rezultat</label>
            <select class="form-select" id="result" name="result">
                <option value="PENDING" ${empty interview or interview.result eq 'PENDING' ? 'selected' : ''}>PENDING</option>
                <option value="PASSED" ${not empty interview and interview.result eq 'PASSED' ? 'selected' : ''}>PASSED</option>
                <option value="FAILED" ${not empty interview and interview.result eq 'FAILED' ? 'selected' : ''}>FAILED</option>
            </select>
        </div>

        <button class="btn btn-primary" type="submit">Salveaza interviu</button>
    </form>

    <c:if test="${not empty interview}">
        <form method="POST" action="${pageContext.request.contextPath}/DeleteInterview" class="d-inline mt-2">
            <input type="hidden" name="application_id" value="${application.id}">
            <button type="submit" class="btn btn-outline-danger btn-sm"
                    onclick="return confirm('Sigur stergi interviul?');">Sterge interviu</button>
        </form>
    </c:if>

    <c:if test="${application.status eq 'ACCEPTED'}">
        <hr>
        <h3>Nota finala</h3>
        <form class="mt-3" method="POST" action="${pageContext.request.contextPath}/SaveGrade">
            <input type="hidden" name="application_id" value="${application.id}">
            <div class="mb-3">
                <label for="value" class="form-label">Notă (1-10)</label>
                <input type="number" step="0.1" min="1" max="10" class="form-control" id="value" name="value"
                       value="${grade.value}">
            </div>
            <button class="btn btn-primary" type="submit">Salveaza nota</button>
        </form>

        <c:if test="${not empty grade}">
            <form method="POST" action="${pageContext.request.contextPath}/DeleteGrade" class="d-inline mt-2">
                <input type="hidden" name="application_id" value="${application.id}">
                <button type="submit" class="btn btn-outline-danger btn-sm"
                        onclick="return confirm('Sigur stergi nota?');">Sterge nota</button>
            </form>
        </c:if>
    </c:if>
</t:pageTemplate>