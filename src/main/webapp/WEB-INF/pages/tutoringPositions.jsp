<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Tutoriat" activePage="tutoring">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Pozitii de tutoriat</h1>
        <c:if test="${pageContext.request.isUserInRole('WRITE_TUTORING')}">
            <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="collapse" data-bs-target="#addForm">Adauga pozitie</button>
        </c:if>
    </div>

    <c:if test="${pageContext.request.isUserInRole('WRITE_TUTORING')}">
        <div class="collapse mt-3" id="addForm">
            <form method="POST" action="${pageContext.request.contextPath}/AddTutoringPosition">
                <div class="mb-3">
                    <label for="title" class="form-label">Titlu</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Descriere</label>
                    <textarea class="form-control" id="description" name="description" rows="2"></textarea>
                </div>

                <button class="btn btn-primary" type="submit">Salveaza</button>
            </form>
        </div>
    </c:if>

    <table class="table table-striped mt-4">
        <thead>
        <tr>
            <th>Titlu</th>
            <th>Descriere</th>
            <th>Student asignat</th>
            <c:if test="${pageContext.request.isUserInRole('WRITE_TUTORING')}"><th></th></c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="tp" items="${tutoringPositions}">
            <tr>
                <td>${tp.title}</td>
                <td>${tp.description}</td>
                <td>${empty tp.studentName ? '-' : tp.studentName}</td>
                <c:if test="${pageContext.request.isUserInRole('WRITE_TUTORING')}">
                    <td>
                        <c:if test="${empty tp.studentName}">
                            <form method="POST" action="${pageContext.request.contextPath}/AssignTutoring" class="d-flex gap-2">
                                <input type="hidden" name="tutoring_position_id" value="${tp.id}">
                                <select class="form-select form-select-sm" name="student_id" required>
                                    <option value="">Alege student...</option>
                                    <c:forEach var="student" items="${unassignedStudents}">
                                        <option value="${student.id}">${student.fullName}</option>
                                    </c:forEach>
                                </select>
                                <button type="submit" class="btn btn-success btn-sm">Asigneaza</button>
                            </form>
                        </c:if>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:pageTemplate>