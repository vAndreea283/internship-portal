<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Studenti" activePage="students">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Studenti inregistrati</h1>
        <c:if test="${pageContext.request.isUserInRole('WRITE_STUDENTS')}">
            <a href="${pageContext.request.contextPath}/AddStudent" class="btn btn-primary btn-lg">Adauga student</a>
        </c:if>
    </div>

    <form method="POST" action="${pageContext.request.contextPath}/Students">
        <c:if test="${pageContext.request.isUserInRole('WRITE_STUDENTS')}">
            <button type="submit" class="btn btn-danger mt-3">Sterge selectate</button>
        </c:if>
        <table class="table table-striped mt-3">
            <thead>
            <tr>
                <c:if test="${pageContext.request.isUserInRole('WRITE_STUDENTS')}"><th></th></c:if>
                <th>Nume</th>
                <th>An de studiu</th>
                <th>Poza</th>
                <th>CV</th>
                <th>Cont</th>
                <c:if test="${pageContext.request.isUserInRole('WRITE_STUDENTS')}"><th></th></c:if>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_STUDENTS')}">
                        <td><input type="checkbox" name="student_ids" value="${student.id}"></td>
                    </c:if>
                    <td>${student.fullName}</td>
                    <td>${student.yearOfStudy}</td>
                    <td><img src="${pageContext.request.contextPath}/StudentPhotos?id=${student.id}"
                             style="width:40px;height:40px;object-fit:cover;" onerror="this.style.display='none'"></td>
                    <td><a href="${pageContext.request.contextPath}/StudentCvs?id=${student.id}">Descarca</a></td>
                    <td>${student.username}</td>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_STUDENTS')}">
                        <td><a class="btn btn-secondary btn-sm"
                               href="${pageContext.request.contextPath}/EditStudent?id=${student.id}">Editeaza</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:pageTemplate>