<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TomSm
  Date: 23/02/2022
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.model.Speler" %>
<%@ page import="domain.db.SpelerDB" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Overzicht</title>
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
    <h1><%= "Overzicht topschutter Belgische competitie" %>
    </h1>
</header>
<main>
<c:choose>
    <%--@elvariable id="alleDieren" type="be.ucll.domain.model.Dier"--%>
    <c:when test="${not empty lijst}">
    <article>
        <table id="tabel" style="width: 100%; padding: 0 0 0 30px">
            <thead>
            <tr>
                <th>Naam</th>
                <th>Doelpunten</th>
                <th>Wedstrijden</th>
                <th>Club</th>
                <th>Pas aan</th>
                <th>Verwijder</th>
            </tr>
            </thead>
            <tbody>
        <c:if test="${not empty lijst}"></c:if>
        <c:forEach var="speler" items="${lijst}">
            <tr>
                <td>${speler.naam}</td>
                <td>${speler.doelpunten}</td>
                <td>${speler.wedstrijden}</td>
                <td>${speler.club}</td>
                <td><a href="Controller?command=PasAanPage&id=${speler.id}" id="Pasaan${speler.naam}">Pas aan</a></td>
                <td><a href="Controller?command=VerwijderPage&id=${speler.id}" id="Verwijder${speler.naam}">Verwijder</a></td>
            </tr>
        </c:forEach>
            </tbody>
        </table>
            </c:when>
            <c:otherwise>
                <p>Er zijn geen spelers</p>
            </c:otherwise>
        </c:choose>
    </article>
</main>
<jsp:include page="Footer.jsp"/>
<jsp:include page="${footer}"/>
</body>
</html>
