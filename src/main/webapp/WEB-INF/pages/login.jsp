<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Login" activePage="login">
  <h1>Autentificare</h1>

  <c:if test="${message != null}">
    <div class="alert alert-danger mt-3">${message}</div>
  </c:if>

  <form class="mt-4" style="max-width: 400px;" method="POST" action="j_security_check">
    <div class="mb-3">
      <label for="j_username" class="form-label">Username</label>
      <input type="text" class="form-control" id="j_username" name="j_username" required>
    </div>

    <div class="mb-3">
      <label for="j_password" class="form-label">Parola</label>
      <input type="password" class="form-control" id="j_password" name="j_password" required>
    </div>

    <button class="w-100 btn btn-primary btn-lg" type="submit">Login</button>
  </form>
  <p class="mt-3"><a href="${pageContext.request.contextPath}/RegisterCompany">Esti o companie? Inregistreaza-te aici</a></p>
</t:pageTemplate>