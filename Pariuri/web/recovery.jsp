<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="user" class="web.WebUser" scope="session" />
        <jsp:setProperty name="user" property="*" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><title>CS:GO password recovery</title></title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/run/recovery">
            <label for="username">Username:</label>
            <input id="username" type="text" name="username" required />
            <label for="email">Email:</label> 
            <input id="email" type="email" name="email" required />
            <input type="submit" value="Done"/>
        </form>
    </body>
</html>
