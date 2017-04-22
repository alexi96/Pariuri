<jsp:directive.page import="java.net.NetworkInterface"/>
<jsp:directive.page import="java.net.InetAddress"/>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bet manager</title>
    </head>
    <body>
        <%
            InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
            if (addr.isLoopbackAddress() || request.getParameter("nosec") != null) {
        %>
        <jsp:include page="WEB-INF/jspf/home.jsp" />
        <%} else {%>
        <jsp:include page="WEB-INF/jspf/unauthorized.jsp" />
        <%}%>
    </body>
</html>
