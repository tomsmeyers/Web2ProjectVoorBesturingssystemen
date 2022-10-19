<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Zoek</title>
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
    <h1><%= "Zoek" %>
    </h1>
</header>
    <main>
        <h2>Toon de kamer met id</h2>

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

        <form method="POST" action="Controller?command=ZoekKamer&id=${kamer.id}" novalidate="novalidate">
            <td><input id="number" type="text" name="number" type="number" required></td>
            <p><input type="submit" value="Zoek"></p>
        </form>
        </article>
    </main>
<jsp:include page="Footer.jsp"/>
<jsp:include page="${footer}"/>
</body>
</html>