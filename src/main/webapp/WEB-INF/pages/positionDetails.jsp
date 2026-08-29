<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Detalii pozitie" activePage="positions">
    <h1>${position.title}</h1>
    <p class="text-muted">${position.companyName}</p>

    <dl class="row mt-4">
        <dt class="col-sm-3">Descriere</dt>
        <dd class="col-sm-9">${position.description}</dd>

        <dt class="col-sm-3">An de studiu tinta</dt>
        <dd class="col-sm-9">${position.yearOfStudyTarget}</dd>

        <dt class="col-sm-3">Numar de locuri</dt>
        <dd class="col-sm-9">${position.numberOfSlots}</dd>

        <dt class="col-sm-3">Durata</dt>
        <dd class="col-sm-9">${position.durationWeeks} saptamani</dd>

        <dt class="col-sm-3">Termen limita aplicare</dt>
        <dd class="col-sm-9">${position.applicationDeadline}</dd>

        <dt class="col-sm-3">Status</dt>
        <dd class="col-sm-9">${position.status}</dd>
    </dl>

    <c:if test="${pageContext.request.isUserInRole('APPLY_POSITIONS')}">
        <form method="POST" action="${pageContext.request.contextPath}/Apply">
            <input type="hidden" name="position_id" value="${position.id}">
            <button type="submit" class="btn btn-success">Aplica</button>
        </form>
    </c:if>

    <a href="${pageContext.request.contextPath}/Positions" class="btn btn-secondary mt-3">Inapoi la lista</a>
</t:pageTemplate>