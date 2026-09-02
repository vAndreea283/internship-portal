<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Editeaza companie" activePage="companies">
    <h1>Editeaza companie</h1>
    <form class="mt-4" method="POST" action="${pageContext.request.contextPath}/EditCompany">
        <input type="hidden" name="id" value="${company.id}">
        <div class="mb-3">
            <label for="name" class="form-label">Nume companie</label>
            <input type="text" class="form-control" id="name" name="name" value="${company.name}" required>
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

        <button class="w-100 btn btn-primary btn-lg" type="submit">Salveaza</button>
    </form>
</t:pageTemplate>