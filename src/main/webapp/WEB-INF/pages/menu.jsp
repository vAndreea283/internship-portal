<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="p-3 mb-3 border-bottom">
    <div class="container-fluid">
        <div class="d-flex flex-wrap align-items-center justify-content-between">
            <a href="${pageContext.request.contextPath}"
               class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <span class="fs-4">Portal Internship</span>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center">
                <li><a href="${pageContext.request.contextPath}"
                       class="nav-link px-2 ${activePage eq 'home' ? 'active' : 'link-dark'}">Acasa</a></li>
                <li><a href="${pageContext.request.contextPath}/about.jsp"
                       class="nav-link px-2 ${activePage eq 'about' ? 'active' : 'link-dark'}">Despre</a></li>

                <%--<li><a href="${pageContext.request.contextPath}/Positions"
                       class="nav-link px-2 ${activePage eq 'positions' ? 'active' : 'link-dark'}">Pozitii</a></li>
                <li><a href="${pageContext.request.contextPath}/Companies"
                       class="nav-link px-2 ${activePage eq 'companies' ? 'active' : 'link-dark'}">Companii</a></li>
                <li><a href="${pageContext.request.contextPath}/Students"
                       class="nav-link px-2 ${activePage eq 'students' ? 'active' : 'link-dark'}">Studenti</a></li>--%>

                <c:if test="${pageContext.request.isUserInRole('READ_POSITIONS')}">
                    <li><a href="${pageContext.request.contextPath}/Positions"
                           class="nav-link px-2 ${activePage eq 'positions' ? 'active' : 'link-dark'}">Pozitii</a></li>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('READ_COMPANIES')}">
                    <li><a href="${pageContext.request.contextPath}/Companies"
                           class="nav-link px-2 ${activePage eq 'companies' ? 'active' : 'link-dark'}">Companii</a></li>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('READ_STUDENTS')}">
                    <li><a href="${pageContext.request.contextPath}/Students"
                           class="nav-link px-2 ${activePage eq 'students' ? 'active' : 'link-dark'}">Studenti</a></li>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('READ_USERS')}">
                    <li><a href="${pageContext.request.contextPath}/Users"
                           class="nav-link px-2 ${activePage eq 'users' ? 'active' : 'link-dark'}">Useri</a></li>
                </c:if>
            </ul>

            <div class="text-end">
                <c:choose>
                    <c:when test="${pageContext.request.getRemoteUser() == null}">
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/Login">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/Logout">Logout</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</header>