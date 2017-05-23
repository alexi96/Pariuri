<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="model.Game"%>
<%@page import="web.WebGame"%>
<%@page import="model.StatisticType"%>
<%@page import="model.Country"%>
<%@page import="controllers.MainController"%>
<%@page import="model.Team"%>
<%@page import="java.util.List"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>

<jsp:useBean id="team" class="model.Team" scope="request" />
<jsp:setProperty name="team" property="name" param="teamName" />
<jsp:setProperty name="team" property="country" param="country" />

<jsp:useBean id="game" class="web.WebGame" scope="request" />
<jsp:setProperty name="game" property="chance" param="chance"/>
<jsp:setProperty name="game" property="dateAsText" param="date"/>
<jsp:setProperty name="game" property="name" param="gameName" />
<jsp:setProperty name="game" property="description" param="description" />

<jsp:useBean id="ticket" scope="request" class="model.ComposedTicket" />
<jsp:setProperty name="ticket" property="id" param="ticketId" />

<%!
    @Resource(name = "jdbc/PariuriDB")
    DataSource ds;
%>
<%
    Connection cn = null;
    try {
        cn = this.ds.getConnection();
%>
<h1>Hello ${pageContext.request.remoteAddr}!</h1>
<form action="${pageContext.request.contextPath}/run/createTeam.jsp" method="post">
    <fieldset>
        <legend>Create team:</legend>
        <label for="teamName">Name</label>
        <input id="teamName" type="text" name="teamName" required />
        <select name="country">
            <%
                List<Country> allCountryes = MainController.getInstance().findCountryes(cn);
                for (Country c : allCountryes) {
            %>
            <option value="<%=c.getId()%>"><%=c.getName()%></option>
            <%}%>
        </select>
        <input type="submit" value="Create team" />
    </fieldset>
</form>
<form action="${pageContext.request.contextPath}/run/createGame.jsp" method="post">
    <fieldset>
        <legend>Create game:</legend>
        <label for="gameName">Name</label>
        <input id="gameName" type="text" name="gameName" required />
        <label for="chance">Chance</label>
        <input id="chance" type="number" name="chance" required />
        <label for="date">Date dd/MM/yyyy HH:mm</label>
        <input id="date" type="datetime" name="date" required />
        <label for="date">Description</label>
        <textarea name="description" required>Description here!</textarea>
        <label for="teamA">Team A</label>
        <select name="teamA">
            <%
                List<Team> allTeams = MainController.getInstance().findTeams(cn);
                for (Team t : allTeams) {
            %>
            <option value="<%=t.getId()%>"><%=t.getName()%></option>
            <%}%>
        </select>
        <label for="teamB">Team B</label>
        <select name="teamB">
            <%
                allTeams = MainController.getInstance().findTeams(cn);
                for (Team t : allTeams) {
            %>
            <option value="<%=t.getId()%>"><%=t.getName()%></option>
            <%}%>
        </select>
        <input type="submit" value="Create game" />
    </fieldset>
</form>
<form action="${pageContext.request.contextPath}/run/createResults.jsp" method="post">
    <fieldset>
        <legend>Create results:</legend>
        <label for="resultGame">Game</label>
        <select id="resultGame" name="resultGame">
            <%
                List<Game> allGm = MainController.getInstance().findFutureGames(cn);
                for (Game t : allGm) {
            %>
            <option value="<%=t.getId()%>"><%=t.getName()%></option>
            <%}%>
        </select>
        <%
            List<StatisticType> allSt = MainController.getInstance().findStatisticTypes(cn);
            int index = 0;
            for (StatisticType t : allSt) {
        %>
        <label for="result<%=index%>"><%=t.getName()%></label>
        <input type="number" id="result<%=index%>" name="result<%=index%>">
        <%
                ++index;
            }
        %>
        <input type="submit" value="Create results" />
    </fieldset>
</form>
<form action="${pageContext.request.contextPath}/run/validateTicket.jsp">
    <fieldset>
        <legend>
            Validate ticket:
        </legend>
        <label for="ticketId">Ticket id</label>
        <input type="number" name="ticketId"/>
        <input type="submit" value="Validate"/>
    </fieldset>
</form>
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