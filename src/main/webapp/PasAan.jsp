<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TomSm
  Date: 16/04/2022
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pas aan</title>
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
    <h1><%= "Pas aan" %>
    </h1>
</header>
<main>
    <article>
        <c:if test="${not empty errors}">
            <div id="error" class="alert alert-danger">
                <ul class="error">
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <p>
        <form method="POST" class="formulier" action="Controller?command=PasSpelerAan&id=${speler.id}" novalidate="novalidate">
            <table>
                <thead>
                <tr>
                    <td>Naam(voornaam + achternaam)*: </td>
                    <td><input id="Name" type="text" name="Name" value="${speler.naam}" class="${naamHasErrors? 'error' : ''}" required></td>
                </tr>
                <tr>
                    <td>Aantal Doelpunten*: </td>
                    <td><input id="Doelpunten" type="text" name="Doelpunten" value="${speler.doelpunten}" class="${doelpuntenHasErrors? 'error' : ''}" required></td>
                </tr>
                <tr>
                    <td>Aantal gespeelde matchen*: </td>
                    <td><input id="Wedstrijden" type="text" name="Wedstrijden" value="${speler.wedstrijden}" class="${wedstrijdenHasErrors? 'error' : ''}" required></td>
                </tr>
                <tr>
                    <td>Club*: </td>
                    <td><select id="Club" name="Club" value="${speler.club}" class="${clubHasErrors? 'error' : ''}" required>
                        <option> </option>
                        <option ${"Anderlecht"==speler.club ? 'selected="selected"' : ''}>Anderlecht</option>
                        <option ${"Antwerp"==speler.club ? 'selected="selected"' : ''} >Antwerp</option>
                        <option ${"Beerschot"==speler.club ? 'selected="selected"' : ''}>Beerschot</option>
                        <option ${"Cercle Brugge"==speler.club ? 'selected="selected"' : ''}>Cercle Brugge</option>
                        <option ${"Club Brugge"==speler.club ? 'selected="selected"' : ''}>Club Brugge</option>
                        <option ${"Charleroi"==speler.club ? 'selected="selected"' : ''}>Charleroi</option>
                        <option ${"Eupen"==speler.club ? 'selected="selected"' : ''}>Eupen</option>
                        <option ${"Genk"==speler.club ? 'selected="selected"' : ''}>Genk</option>
                        <option ${"Gent"==speler.club ? 'selected="selected"' : ''}>Gent</option>
                        <option ${"Kortrijk"==speler.club ? 'selected="selected"' : ''}>Kortrijk</option>
                        <option ${"Oud-Heverlee Leuven"==speler.club ? 'selected="selected"' : ''}>Oud-Heverlee Leuven</option>
                        <option ${"KV Mechelen"==speler.club ? 'selected="selected"' : ''}>KV Mechelen</option>
                        <option ${"KV Oostende"==speler.club ? 'selected="selected"' : ''}>KV Oostende</option>
                        <option ${"Seraing"==speler.club ? 'selected="selected"' : ''}>Seraing</option>
                        <option ${"STVV"==speler.club ? 'selected="selected"' : ''}>STVV</option>
                        <option ${"Standard"==speler.club ? 'selected="selected"' : ''}>Standard</option>
                        <option ${"Union"==speler.club ? 'selected="selected"' : ''}>Union</option>
                        <option ${"Zulte Waregem"==speler.club ? 'selected="selected"' : ''}>Zulte Waregem</option>
                    </select>

                    </td>
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
