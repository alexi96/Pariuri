<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="model.Country"%>
<%@page import="controllers.MainController"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
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
        <jsp:useBean id="team" class="model.Team" scope="request" />
        <jsp:setProperty name="team" property="name" param="teamName" />
        <jsp:setProperty name="team" property="country" param="country" />
        <%
            MainController con = MainController.getInstance();
            boolean succ = con.createTeam(team, cn);
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if (succ) {%>
        <title>Team ${team.name} created!</title>
        <%} else {%>
        <title>Error creating team</title>
        <%}%>
    </head>
    <body>
        <%if (succ) {
                Country c = con.findCountry(team.getCountry(), cn);
        %>
        <h1>Team ${team.name} created!</h1>
        <p>
            They are from <%=c.getName()%>!
        </p>
        <%} else {%>
        <h1>Error creating team</h1>
        <%}%>
        <form action="${pageContext.request.contextPath}/manager.jsp" method="post">
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