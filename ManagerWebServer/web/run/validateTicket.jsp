<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="model.Ticket"%>
<%@page import="controllers.MainController"%>
<%@page import="connection.Connection"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="ticket" scope="request" class="model.ComposedTicket" />
        <jsp:setProperty name="ticket" property="id" param="ticketId" />
        <%
            Connection con = MainController.getInstance().getConnection();
            Map<Ticket, Float> winnings = con.validateTicket(ticket.getId());
            boolean succ = winnings != null;
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if (succ) {%>
        <title>Ticket ${ticket} validated!</title>
        <%} else {%>
        <title>Error validateing ticket!</title>
        <%}%>
    </head>
    <body>
        <%if (succ) {%>
        <h1>Ticket ${ticket} validated!</h1>
        <%
            double sum = winnings.values().stream().mapToDouble((v) -> (double) v).sum();
        %>
        <p>You won <%=sum%>$</p>
        <%
            Set<Ticket> ts = winnings.keySet();
            for (Ticket t : ts) {
        %>
        <p>
            <%=t.getId()%>: <%=winnings.get(t)%>
        </p>
        <%}%>
        <%} else {%>
        <h1>Error creating team</h1>
        <%}%>
        <form action="${pageContext.request.contextPath}/index.jsp" method="post">
            <input type="submit" value="Back">
        </form>
    </body>
</html>
