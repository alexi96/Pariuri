<%@page import="model.Team"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="game" class="web.WebGame" scope="request" />
        <jsp:setProperty name="game" property="chance" param="chance" />
        <jsp:setProperty name="game" property="dateAsText" param="date" />
        <jsp:setProperty name="game" property="name" param="gameName" />
        <jsp:setProperty name="game" property="description" param="description" />
        <%
            int ta = Integer.parseInt(request.getParameter("teamA"));
            int tb = Integer.parseInt(request.getParameter("teamB"));
            boolean succ = game.create(ta, tb);
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if (succ) {%>
        <title>Game ${game.name} created!</title>
        <%} else {%>
        <title>Error creating game</title>
        <%}%>
    </head>
    <body>
        <%if (succ) {
        %>
        <h1>Game ${game.name} created!</h1>
        <p>
            ${game.chance}
        </p>
        <p>
            ${game.description}
        </p>
        <p>
        </p>
        <%} else {%>
        <h1>Error creating game</h1>
        <%}%>
        <form action="${pageContext.request.contextPath}/index.jsp" method="post">
            <input type="submit" value="Back">
        </form>
    </body>
</html>
