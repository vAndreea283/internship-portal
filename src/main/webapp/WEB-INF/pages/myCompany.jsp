<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Compania mea" activePage="myCompany">
  <h1>${company.name}</h1>
  <p>Status: <strong>${company.status}</strong></p>
  <p>${company.description}</p>
</t:pageTemplate>