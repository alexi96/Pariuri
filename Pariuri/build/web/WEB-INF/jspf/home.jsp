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
%>
<h1>Welcome ${user.username}</h1>
<form action="${pageContext.request.contextPath}/run/logout" method="post">
    <input type="submit" value="Log out">
</form>
<div>
    <header>
        Your tickets
    </header>
    <%
        MainController con = MainController.getInstance();
        Set<ComposedTicket> all = con.findComposedTickets(new User(user.getId()), cn);
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