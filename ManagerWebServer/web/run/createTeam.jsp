<%@page import="model.Country"%>
<%@page import="connection.Connection"%>
<%@page import="controllers.MainController"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="team" class="model.Team" scope="page" />
        <jsp:setProperty name="team" property="name" param="teamName" />
        <jsp:setProperty name="team" property="country" param="country" />
        <%
            Connection con = MainController.getInstance().getConnection();
            boolean succ = con.createTeam(team);
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
            Country c = con.findCountry(team.getCountry());
        %>
        <h1>Team ${team.name} created!</h1>
        <p>
            They are from <%=c.getName()%>!
        </p>
        <%} else {%>
        <h1>Error creating team</h1>
        <%}%>
        <form action="${pageContext.request.contextPath}/index.jsp" method="post">
            <input type="submit" value="Back">
        </form>
    </body>
</html>
