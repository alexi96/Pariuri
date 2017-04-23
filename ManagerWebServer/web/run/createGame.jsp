<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="game" class="web.WebGame" scope="request" />
        <jsp:setProperty name="game" property="chance" param="chance" />
        <jsp:setProperty name="game" property="dateAsText" param="date" />
        <jsp:setProperty name="game" property="name" param="gameName" />
        <jsp:setProperty name="game" property="description" param="description" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
