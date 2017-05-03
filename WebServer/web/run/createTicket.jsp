<%@page import="model.Ticket"%>
<%@page import="model.User"%>
<%@page import="controllers.MainController"%>
<%@page import="connection.Connection"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="user" class="web.WebUser" scope="session" />
        <jsp:setProperty name="user" property="*" />
        <jsp:useBean id="conposedTicket" class="web.WebConposedTicket" scope="session"/>
        <jsp:setProperty name="conposedTicket" property="user" param="user" />
        <%
            Connection con = MainController.getInstance().getConnection();
            Integer ticketId = con.createComposedTiket(new User(user.getId()), conposedTicket.getTickets());

            boolean succ = ticketId != null;
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if (succ) {%>
        <title>Ticket <%=ticketId%> created for ${user.username}</title> 
        <%} else {%>
        <title>Error creating ticket!</title> 
        <%}%>
    </head>
    <body>
        <h1>Ticket <%=ticketId%> created for ${user.username}</h1> 
        <%
            if (succ) {
                for (Ticket t : conposedTicket.getTickets()) {
        %>
        <p><%=t.getAmmount()%> <%=t.getValue()%> <%=con.findGame(t.getGame()).getName()%> <%=con.findStatisticType(t.getType()).getName()%> <%=t.getOperation()%></p>
        <%
            }
            conposedTicket.getTickets().clear();
        } else {
        %>
        <h1>
            Unable to create ticket!
        </h1>
        <%
            }
        %>
        <form action="${pageContext.request.contextPath}/index.jsp" method="post">
            <input type="submit" value="Home"/>
        </form>
    </body>
</html>
