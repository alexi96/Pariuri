<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Game"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="model.StatisticType"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="model.Ticket"%>
<%@page import="controllers.MainController"%>
<%@page import="java.util.Set"%>
<%@page import="model.ComposedTicket"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<jsp:useBean id="user" class="web.WebUser" scope="session" />
<jsp:setProperty name="user" property="*" />
<%!
    @Resource(name = "jdbc/PariuriDB")
    private DataSource ds;
%>
<%
    Connection cn = null;
    try {
        cn = this.ds.getConnection();

        MainController con = MainController.getInstance();
        Set<ComposedTicket> all = con.findComposedTickets(new User(user.getId()), cn);
%>
<h1>Welcome ${user.username}</h1>
<form action="${pageContext.request.contextPath}/run/logout" method="post">
    <input type="submit" value="Log out">
</form>
<form action="${pageContext.request.contextPath}/allStatistics.jsp" method="post">
    <input type="submit" value="See all games">
</form>
<div>
    <h1>
        Your games
    </h1>
    <%
        Set<Game> myGames = con.findGames(new User(user.getId()), cn);
        for (Game g : myGames) {
            Collection<Ticket> wonTs = con.findTickets(g, true, cn);
            Collection<Ticket> lostTs = con.findTickets(g, false, cn);
            float wonVal = con.findMonay(g, true, cn);
            float lostVal = con.findMonay(g, false, cn);
    %>
    <div>
        <h2>
            <%=g%>
        </h2>
        <p>
            Simple ticket win/lost: <%=wonTs.size()%>/<%=lostTs.size()%> <%=(float) wonTs.size() * 100 / (float) (lostTs.size() + wonTs.size())%>%
        </p>
        <p>
            Sum involved in game win/lost: <%=wonVal%>/<%=lostVal%> <%=wonVal * 100 / (wonVal + lostVal)%>%
        </p>
    </div>
    <%
        }
    %>
</div>
<div>
    <h1>
        Your tickets
    </h1>
    <%
        for (ComposedTicket ct : all) {
            String style = "background-color: " + (ct.isValidated() ? "green" : "");
    %>
    <div class="ticket">
        <h2 style="<%=style%>"><%=ct%></h2>
        <%
            List<Ticket> ts = con.findTickets(ct, cn);
            for (Ticket t : ts) {
                StatisticType st = con.findStatisticType(t.getType(), cn);
                Game g = con.findGame(t.getGame(), cn);
                out.println("<p>");
                out.println(t.getId() + " " + t.getValue() + " " + g.getName() + " " + t.getOperation() + " " + st.getName());
                out.println("</p>");
            }
        %>
    </div>
    <%
        }
    %>
</div>
<form action="${pageContext.request.contextPath}/createTicket.jsp" method="post">
    <input type="submit" value="Create ticket" />
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