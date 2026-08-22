<%@tag description="base page template" pageEncoding="UTF-8"%> <%--citeste fisierul JSP ca UTF-8--%>
<%@attribute name="pageTitle"%> <%--defineste un atribut pe care pagina care foloseste template-ul il poate trimite--%>
<%@attribute name="activePage"%> <%--!!!--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <%--interpreteaza HTML-ul primit ca UTF-8--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"> <%--responsive design--%>
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body class="d-flex flex-column min-vh-100"> <%--!!!--%>
    <jsp:include page="/WEB-INF/pages/menu.jsp" />
    <main class="container-fluid mt-5 flex-grow-1"> <%--!!!--%>
        <jsp:doBody/>
    </main>

    <jsp:include page="/WEB-INF/pages/footer.jsp" /> <%--!!!--%>
    <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>
</body>
</html>