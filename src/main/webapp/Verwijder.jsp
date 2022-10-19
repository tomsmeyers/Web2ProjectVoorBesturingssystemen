<%--
  Created by IntelliJ IDEA.
  User: TomSm
  Date: 17/03/2022
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verwijder</title>
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
    <h1><%= "Verwijder" %>
    </h1>
</header>
<main>
    <article>
<h2 style="padding: 0 30px">Ben je zeker dat je speler ${speler.naam} wilt verwijderen?</h2>
<form method="POST" action="Controller?command=Verwijder&id=${param.id}">
    <button type="submit" value="Verstuur" onclick="" id="Ja">Ja</button>
</form>
<form method="POST" action="Controller?command=Overzicht">
    <button type="submit" value="Verstuur" onclick="" id="Nee">Nee</button>
</form>
    </article>
</main>
<jsp:include page="Footer.jsp"/>
<jsp:include page="${footer}"/>
</body>
</html>

