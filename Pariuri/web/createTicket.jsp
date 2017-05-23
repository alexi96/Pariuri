<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="model.Team"%>
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
        <jsp:setProperty name="conposedTicket" property="ammount" param="ammount" />
        <jsp:useBean id="ticket" class="model.Ticket" scope="request"/>
        <jsp:setProperty name="ticket" property="*" />
        <%!
            @Resource(name="jdbc/PariuriDB")
            private DataSource ds;
        %>
        <%
            Connection cn = null;
            try {
                cn = this.ds.getConnection();
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/createTicketStyle.css" rel="stylesheet" type="text/css"/>
        <title>Create ticket</title>
    </head>
    <body>
        <%
            MainController con = MainController.getInstance();

            List<Game> allGm = con.findFutureGames(cn);
            List<StatisticType> allSt = con.findStatisticTypes(cn);

            if (allGm.isEmpty()) {
        %>
        <h1>
            No games available!
        </h1>
        <%
        } else {
            if (ticket.getGame() != null) {
                Ticket t = Conversion.model(ticket, Ticket.class);
                conposedTicket.getTickets().add(t);
            }

            for (Ticket t : conposedTicket.getTickets()) {
                StatisticType st = con.findStatisticType(t.getType(), cn);
        %>
        <p><%=t.getValue()%> <%=con.findGame(t.getGame(), cn).getName()%> <%=st.getName()%> <%=t.getOperation()%></p>
        <%}%>
        <form method="post">
            <label for="value">Value:</label>
            <input id="value" type="number" name="value" />
            <label for="type">Bet type:</label>
            <select id="type" name="type">
                <%
                    for (StatisticType t : allSt) {
                %>
                <option value="<%=t.getId()%>"><%=t.getName()%></option>
                <%}%>
            </select>
            <label for="game">Game:</label>
            <select id="game" name="game">
                <%
                    for (Game t : allGm) {
                        Team[] ts = con.findTeams(t, cn);
                %>
                <option value="<%=t.getId()%>"><%=t.getName() + " " + t.getChance() + "%"%>: <%=ts[0].getName()%> vs <%=ts[1].getName()%></option>
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
            <label for="ammount">Ammount:</label>
            <input id="ammount" type="number" name="ammount" />
            <input type="submit" value="Done" />
        </form>
        <%
            }
        %>
        <form action="${pageContext.request.contextPath}/index.jsp" method="post" >
            <span>
                <input type="submit" value="Cancel" class="cancelbtn"/>
            </span>
        </form>
        <table id="bettingRules">
            <caption>Betting rules</caption>
            <thead>
                <tr>
                    <td>

                    </td>
                    <%
                        for (StatisticType t : allSt) {
                    %>
                    <td>
                        <%=t.getName()%>
                    </td>
                    <%}%>
                </tr>
            </thead>
            <tr>
                <td>
                    Deviation
                </td>
                <%
                    for (StatisticType t : allSt) {
                %>
                <td>
                    <%=t.getDeviation()%>
                </td>
                <%}%>
            </tr>
            <tr>
                <td>
                    Exact pay
                </td>
                <%
                    for (StatisticType t : allSt) {
                %>
                <td>
                    <%=100f * t.getExactMultiply()%>%
                </td>
                <%}%>
            </tr>
            <tr>
                <td>
                    Medium pay
                </td>
                <%
                    for (StatisticType t : allSt) {
                %>
                <td>
                    <%=t.getMediumMultiply() * 100f%>%
                </td>
                <%}%>
            </tr>
            <tr>
                <td>
                    Far pay
                </td>
                <%
                    for (StatisticType t : allSt) {
                %>
                <td>
                    <%=t.getFarMultiply() * 100f%>%
                </td>
                <%}%>
            </tr>
        </table>
    </body>
</html>
<%
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
%>