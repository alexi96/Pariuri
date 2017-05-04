<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="connection.Connection"%>
<%@page import="model.Ticket"%>
<%@page import="controllers.MainController"%>
<%@page import="java.util.Set"%>
<%@page import="model.ComposedTicket"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<jsp:useBean id="user" class="web.WebUser" scope="session" />
<jsp:setProperty name="user" property="*" />
<h1>Welcome ${user.username}</h1>
<form action="${pageContext.request.contextPath}/run/logout" method="post">
    <input type="submit" value="Log out">
</form>
<div>
    <header>
        Your tickets
    </header>
    <div>
        <%
            Connection con = MainController.getInstance().getConnection();
            Set<ComposedTicket> all = con.findComposedTickets(new User(user.getId()));
            for (ComposedTicket ct : all) {
        %>
        <h2><%=ct%></h2>
        <p>
            <%
                List<Ticket> ts = con.findTickets(ct);
                for (Ticket t : ts) {
                    out.println(t.getId() + " " + t.getValue() + " " + con.findGame(t.getGame()).getName() + " " + t.getOperation() + " " + con.findStatisticType(t.getType()).getName());
                }
            %>
        </p>
    </div>
    <%}%>
</div>
<form action="${pageContext.request.contextPath}/createTicket.jsp" method="post">
    <input type="submit" value="Create ticket" />
</form>
