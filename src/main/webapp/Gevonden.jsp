<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Speler" %><%--
  Created by IntelliJ IDEA.
  User: TomSm
  Date: 17/03/2022
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gevonden</title>
    <link rel="stylesheet" href="CSS/Normalize.css">
    <link rel="stylesheet" href="CSS/style.css">
    <meta charset="utf-8"/>
</head>
<body>
<figure>
    <img src="Fotos/grass-gebe5d6a3e_1280.jpg" alt id="banner">
</figure>
<header>
    <jsp:include page="Navigation.jsp"/>
    <h1><%= "Resultaat" %>
    </h1>
</header>
<main>
    <article>
        <c:if test="${speler != null}">
    <p>We vonden volgende speler:</p>
    <ul style="margin: 0 30px; list-style: none">
        <li>Naam: ${speler.naam}</li>
        <li>Doelpunten: ${speler.doelpunten}</li>
        <li>Wedstrijden: ${speler.wedstrijden}</li>
        <li>Club: ${speler.club}</li>
    </ul>
        </c:if>
        <c:if test="${speler == null}">
    <p>Helaas, we konden geen speler met naam ${naam} vinden. </p>
        </c:if>
    </article>
</main>
<jsp:include page="Footer.jsp"/>
<jsp:include page="${footer}"/>
</body>
</html>

