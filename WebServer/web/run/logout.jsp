<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<jsp:useBean id="user" class="web.WebUser" scope="session" />
<jsp:setProperty name="user" property="*" />
<%
    user.logOut();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="0; url=${pageContext.request.contextPath}/index.jsp">
    </head>
    <body>
        <p>The page has moved to: <a href="${pageContext.request.contextPath}/index.jsp">this page</a></p>
    </body>
</html>
