<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Studentenkamer" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Gevonden kamer</title>
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
  <h1><%= "Gevonden" %>
  </h1>
</header>
<main>
  <article>
    <h2>Gevonden kamer</h2>
    <p>Je zocht naar volgende kamer met id ${kamer.id}: ${kamer.straat} ${kamer.huisnummer} (kamer ${kamer.kamernummer}) kost €${kamer.kostPerMaand} per maand</p>
  </article>
</main>
<jsp:include page="Footer.jsp"/>
<jsp:include page="${footer}"/>
</body>
</html>
