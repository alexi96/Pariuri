<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />  
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="user" class="web.WebUser" scope="session" />
        <jsp:setProperty name="user" property="*" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/style.css" rel="stylesheet" type="text/css">
        <%if (user.getId() == null) {%>
        <title>Pariuri CS:GO</title>
        <%} else {%>
        <title>Pariuri CS:GO ${user.username}</title>
        <%}%>
    </head>
    <body>
        <%if (user.getId() == null) {%>
        <jsp:include page="/WEB-INF/jspf/log.jsp" />
        <%} else {%>
        <jsp:include page="/WEB-INF/jspf/home.jsp" />
        <%}%>
    </body>
</html>
