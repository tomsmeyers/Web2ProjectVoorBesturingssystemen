<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TomSm
  Date: 22/02/2022
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voeg Toe</title>
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
    <h1><%= "Voeg een speler toe" %>
    </h1>
</header>
<main>
    <article>
        <c:if test="${not empty errors}">
            <div id="error" class="alert alert-danger">
                <ul class="error" style="margin: 10px; border-radius: 5px;">
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="POST" class="formulier" action="Controller?command=VoegSpelerToe" novalidate="novalidate">
            <table>
            <thead>
                <tr>
                    <td>Naam(voornaam + achternaam)*: </td>
                    <td><input id="Name" type="text" name="Name" value="${VorigeNaam}" class="${naamHasErrors? 'error' : ''}" required></td>
                </tr>
                <tr>
                    <td>Aantal Doelpunten*: </td>
                    <td><input id="Doelpunten" type="text" name="Doelpunten" value="${VorigeDoelpunten}" class="${doelpuntenHasErrors? 'error' : ''}" required></td>
                </tr>
                <tr>
                    <td>Aantal gespeelde matchen*: </td>
                    <td><input id="Wedstrijden" type="text" name="Wedstrijden" value="${VorigeWedstrijden}" class="${wedstrijdenHasErrors? 'error' : ''}" required></td>
                </tr>
                <tr>
                    <td>Club*: </td>
                    <td><select id="Club" name="Club" value="${VorigeClub}" class="${clubHasErrors? 'error' : ''}" required>
                        <option> </option>
                        <option>Anderlecht</option>
                        <option>Antwerp</option>
                        <option>Beerschot</option>
                        <option>Cercle Brugge</option>
                        <option>Club Brugge</option>
                        <option>Charleroi</option>
                        <option>Eupen</option>
                        <option>Genk</option>
                        <option>Gent</option>
                        <option>Kortrijk</option>
                        <option>Oud-Heverlee Leuven</option>
                        <option>KV Mechelen</option>
                        <option>KV Oostende</option>
                        <option>Seraing</option>
                        <option>STVV</option>
                        <option>Standard</option>
                        <option>Union</option>
                        <option>Zulte Waregem</option>
                    </select></td>
                </tr>
                <tr>
                    <td></td>
                    <td><br><input id="button" type="submit" value="Verstuur"></td>
                </tr>
                </thead>
            </table>
        </form>
    </article>
</main>
<jsp:include page="Footer.jsp"/>
<jsp:include page="${footer}"/>
</body>
</html>
