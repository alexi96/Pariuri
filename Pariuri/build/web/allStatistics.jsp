<%@page import="model.Ticket"%>
<%@page import="model.Ticket"%>
<%@page import="java.util.Collection"%>
<%@page import="model.Game"%>
<%@page import="model.User"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Set"%>
<%@page import="controllers.MainController"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.annotation.Resource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%!
        @Resource(name = "jdbc/PariuriDB")
        private DataSource ds;
    %>
    <%
        Connection cn = null;
        try {
            cn = this.ds.getConnection();

            MainController con = MainController.getInstance();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All games</title>
    </head>
    <body>
        <h1>All games</h1>
        <%
            Collection<Game> myGames = con.findGames(cn);
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
        <form action="${pageContext.request.contextPath}/index.jsp" method="post">
            <input type="submit" value="Back">
        </form>
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