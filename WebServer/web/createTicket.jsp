<%@page import="model.Team"%>
<%@page import="connection.Connection"%>
<%@page import="utilities.Conversion"%>
<%@page import="model.Game"%>
<%@page import="model.StatisticType"%>
<%@page import="controllers.MainController"%>
<%@page import="java.util.List"%>
<%@page import="model.Ticket"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="conposedTicket" class="web.WebConposedTicket" scope="session"/>
        <jsp:setProperty name="conposedTicket" property="user" param="user" />
        <jsp:useBean id="ticket" class="model.Ticket" scope="session"/>
        <jsp:setProperty name="ticket" property="*" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create ticket</title>
    </head>
    <body>
        <%
            Connection con = MainController.getInstance().getConnection();

            if (ticket.getGame() != null) {
                Ticket t = Conversion.model(ticket, Ticket.class);
                conposedTicket.getTickets().add(t);
            }
            for (Ticket t : conposedTicket.getTickets()) {
        %>
        <p><%=t.getAmmount()%> <%=t.getValue()%> <%=con.findGame(t.getGame()).getName()%> <%=con.findStatisticType(t.getType()).getName()%> <%=t.getOperation()%></p>
        <%}%>
        <form method="post">
            <label for="ammount">Ammount:</label>
            <input id="ammount" type="number" name="ammount" />
            <label for="value">Value:</label>
            <input id="value" type="number" name="value" />
            <label for="type">Bet type:</label>
            <select id="type" name="type">
                <%
                    List<StatisticType> allSt = con.findStatisticTypes();
                    for (StatisticType t : allSt) {
                %>
                <option value="<%=t.getId()%>"><%=t.getName()%></option>
                <%}%>
            </select>
            <label for="game">Game:</label>
            <select id="game" name="game">
                <%
                    List<Game> allGm = con.findFutureGames();
                    for (Game t : allGm) {
                        Team[] ts = con.findTeams(t);
                %>
                <option value="<%=t.getId()%>"><%=t.getName()%>: <%=ts[0].getName()%> vs <%=ts[1].getName()%></option>
                <%}%>
            </select>
            <label for="operation">Operation:</label>
            <select id="operation" name="operation">
                <option value="0">Exact</option>
                <option value="1">Medium</option>
                <option value="2">Far</option>
            </select>
            <input type="submit" value="Add simple ticket" />
        </form>
        <form action="${pageContext.request.contextPath}/run/createTicket.jsp" method="post">
            <input type="submit" value="Done" />
        </form>
    </body>
</html>
