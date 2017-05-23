<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="model.ComposedTicket"%>
<%@page import="model.Ticket"%>
<%@page import="model.User"%>
<%@page import="controllers.MainController"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html>
    <head>
        <%!
            @Resource(name = "jdbc/PariuriDB")
            private DataSource ds;
        %>
        <%
            Connection cn = null;
            try {
                cn = this.ds.getConnection();
        %>
        <jsp:useBean id="user" class="web.WebUser" scope="session" />
        <jsp:setProperty name="user" property="*" />
        <jsp:useBean id="conposedTicket" class="web.WebConposedTicket" scope="session"/>
        <jsp:setProperty name="conposedTicket" property="user" param="user" />
        <jsp:setProperty name="conposedTicket" property="ammount" param="ammount" />
        <%
            MainController con = MainController.getInstance();
            ComposedTicket ct = new ComposedTicket();
            ct.setUser(user.getId());
            ct.setAmmount(conposedTicket.getAmmount());
            Integer ticketId = con.createComposedTicket(ct, conposedTicket.getTickets(), cn);

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
        <%
            if (succ) {
        %>
        <h1>${conposedTicket.ammount}$ ticket <%=ticketId%> created for ${user.username}</h1> 
        <%
            for (Ticket t : conposedTicket.getTickets()) {
        %>
        <p><%=t.getValue()%> <%=con.findGame(t.getGame(), cn).getName()%> <%=con.findStatisticType(t.getType(), cn).getName()%> <%=t.getOperation()%></p>
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