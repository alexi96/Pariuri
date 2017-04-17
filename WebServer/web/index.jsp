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
                    <div id="form">
                        <div class="imgcontainer">
                            <img src="resources/csgobet.png" class="imagine "/>
                        </div>
                        <form action="run/log.jsp" method="post" >
                            <div class="container">
                                Username: <input type="text" name="username" required/>
                                Password: <input type="password" name="password" required/>
                                <input type="submit" value="Login"/>
                            </div>
                        </form>
                        <form action="create.jsp" class="container" style="background-color:#f1f1f1">
                            <input type="submit" value="Create"/>
                            <input type="submit" value="Cancel"/>
                            <span class="psw">Forgot <a>password</a></span>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <h1>Welcome <jsp:getProperty name="user" property="username"/></h1>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
