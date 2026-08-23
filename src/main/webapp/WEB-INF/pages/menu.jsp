<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                <li><a href="${pageContext.request.contextPath}/Positions"
                       class="nav-link px-2 ${activePage eq 'positions' ? 'active' : 'link-dark'}">Pozitii</a></li>
                <li><a href="${pageContext.request.contextPath}/Companies"
                       class="nav-link px-2 ${activePage eq 'companies' ? 'active' : 'link-dark'}">Companii</a></li>
                <li><a href="${pageContext.request.contextPath}/Students"
                       class="nav-link px-2 ${activePage eq 'students' ? 'active' : 'link-dark'}">Studenti</a></li>
            </ul>

            <div class="text-end">
                <a href="${pageContext.request.contextPath}/Login" class="btn btn-outline-primary">Login</a>
            </div>
        </div>
    </div>
</header>