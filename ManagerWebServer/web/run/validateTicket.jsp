<%@page import="java.util.Collection"%>
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
            Float winnings = con.validateTicket(ticket.getId());
            boolean succ = winnings != null;
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if (succ) {%>
        <title>Ticket ${ticket.id} validated!</title>
        <%} else {%>
        <title>Error validateing ticket!</title>
        <%}%>
    </head>
    <body>
        <%if (succ) {%>
        <h1>Ticket ${ticket.id} validated!</h1>
        <p>
            You won <%=winnings%>$!
        </p>
        <%} else {%>
        <h1>Error validating ticket!</h1>
        <%}%>
        <form action="${pageContext.request.contextPath}/index.jsp" method="post">
            <input type="submit" value="Back">
        </form>
    </body>
</html>
