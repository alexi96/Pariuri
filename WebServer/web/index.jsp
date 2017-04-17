<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />  
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="user" class="web.WebUser" scope="session" >
            <jsp:setProperty name="user" property="*" />
        </jsp:useBean>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css">
        <c:choose>
            <c:when test="${user.id == null}">
                <title>Pariuri SC:GO</title>
            </c:when>
            <c:otherwise>
                <title>Pariuri SC:GO ${user.username}</title>
            </c:otherwise>
        </c:choose>
    </head>
    <body>
        <div id="upd">
            <c:choose>
                <c:when test="${user.id == null}">
                    <jsp:include page="WEB-INF/jspf/log.jspf" />
                </c:when>
                <c:otherwise>
                    <h1>Welcome <jsp:getProperty name="user" property="username"/><jsp:getProperty name="user" property="id"/></h1>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
