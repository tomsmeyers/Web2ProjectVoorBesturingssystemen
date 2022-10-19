<%@ page import="domain.model.Speler" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Topschutter Belgische competitie</title>
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
    <h1><%= "Topschutter Belgische competitie" %>
    </h1>
</header>
<main>
    <article>
        <p>Op deze website kan u de topscoorder van de Belgische competitie vinden. Hier wordt hun naam genoteerd, hoeveel goalen ze al gescoord hebben
            , hoeveel wedstrijden ze al gespeeld hebben en voor welke club ze spelen.</p>
        <br>
        <br>
        <p>De topschutter is ${topscoorder}</p>
        <p><a href="Controller?command=AanpassenOngedaanMaken">Laatste wijziging ongedaan maken</a></p>
        <p>${foutmelding}</p>
    </article>
</main>
<jsp:include page="Footer.jsp"/>
<jsp:include page="${footer}"/>
</body>
</html>