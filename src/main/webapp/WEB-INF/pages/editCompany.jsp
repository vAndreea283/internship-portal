<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Editeaza companie" activePage="companies">
    <h1>Editeaza companie</h1>
    <form class="needs-validation mt-4"
          novalidate
          method="POST"
          action="${pageContext.request.contextPath}/EditCompany">

        <input type="hidden" name="id" value="${company.id}">
        <div class="mb-3">
            <label for="name" class="form-label">Nume companie</label>
            <input type="text" class="form-control" id="name" name="name" value="${company.name}" required>
            <div class="invalid-feedback">Numele este obligatoriu!</div>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Descriere</label>
            <textarea class="form-control" id="description" name="description" rows="3">${company.description}</textarea>
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select class="form-select" id="status" name="status" required>
                <option value="PENDING" ${company.status eq 'PENDING' ? 'selected' : ''}>PENDING</option>
                <option value="APPROVED" ${company.status eq 'APPROVED' ? 'selected' : ''}>APPROVED</option>
                <option value="REJECTED" ${company.status eq 'REJECTED' ? 'selected' : ''}>REJECTED</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="user_id" class="form-label">Cont asociat</label>
            <select class="form-select" id="user_id" name="user_id" required>
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}" ${user.id eq company.userId ? 'selected' : ''}>${user.username}</option>
                </c:forEach>
            </select>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Salvează</button>
    </form>
</t:pageTemplate>