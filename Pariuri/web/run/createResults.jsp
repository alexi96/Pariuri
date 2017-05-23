<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="java.util.List"%>
<%@page import="model.StatisticType"%>
<%@page import="model.Game"%>
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
            int gid = Integer.parseInt(request.getParameter("resultGame"));
            List<StatisticType> allSt = con.findStatisticTypes(cn);
            int sz = allSt.size();
            float[] results = new float[sz];
            for (int i = 0; i < sz; ++i) {
                results[i] = Float.parseFloat(request.getParameter("result" + i));
            }
            boolean succ = con.createScore(new Game(gid), cn, results);
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