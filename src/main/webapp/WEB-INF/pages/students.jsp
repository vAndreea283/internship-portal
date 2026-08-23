<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Studenti" activePage="students">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Studenti inregistrati</h1>
        <a href="${pageContext.request.contextPath}/AddStudent" class="btn btn-primary btn-lg">Adauga student</a>
    </div>

    <form method="POST" action="${pageContext.request.contextPath}/Students">
        <button type="submit" class="btn btn-danger mt-3">Sterge selectate</button>
        <table class="table table-striped mt-3">
            <thead>
            <tr>
                <th></th>
                <th>Nume</th>
                <th>An de studiu</th>
                <th>CV</th>
                <th>Cont</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td><input type="checkbox" name="student_ids" value="${student.id}"></td>
                    <td>${student.fullName}</td>
                    <td>${student.yearOfStudy}</td>
                    <td>${student.cvPath}</td>
                    <td>${student.username}</td>
                    <td><a class="btn btn-secondary btn-sm"
                           href="${pageContext.request.contextPath}/EditStudent?id=${student.id}">Editeaza</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:pageTemplate>