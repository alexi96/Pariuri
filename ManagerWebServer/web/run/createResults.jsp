<%@page import="java.util.List"%>
<%@page import="model.StatisticType"%>
<%@page import="model.Game"%>
<%@page import="connection.Connection"%>
<%@page import="controllers.MainController"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="team" class="model.Team" scope="request" />
        <jsp:setProperty name="team" property="name" param="teamName" />
        <jsp:setProperty name="team" property="country" param="country" />
        <%
            Connection con = MainController.getInstance().getConnection();
            int gid = Integer.parseInt(request.getParameter("resultGame"));
            List<StatisticType> allSt = con.findStatisticTypes();
            int sz = allSt.size();
            float[] results = new float[sz];
            for (int i = 0; i < sz; ++i) {
                results[i] = Float.parseFloat(request.getParameter("result" + i));
            }
            boolean succ = con.createScore(new Game(gid), results);
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if (succ) {%>
        <title>Resuts created!</title>
        <%} else {%>
        <title>Error creating results!</title>
        <%}%>
    </head>
    <body>
        <%if (succ) {%>
        <h1>Resuts created!</h1>
        <%
            int index = 0;
            for (StatisticType t : allSt) {%>
            <%=t.getName()%> <%=results[index]%>
        <%
            ++index;
            }
        %>
        <%} else {%>
        <h1>Error creating results!</h1>
        <%}%>
        <form action="${pageContext.request.contextPath}/index.jsp" method="post">
            <input type="submit" value="Back">
        </form>
    </body>
</html>
