<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="created" scope="session" class="web.WebUser" />
        <jsp:setProperty name="created" property="*" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/createstyle.css" rel="stylesheet" type="text/css"/>
        <title>Create account</title>
    </head>
    <body>
        <div id="left"></div>
        <div id="right"></div>
        <div id="top"></div>
        <div id="bottom"></div>
        <form id="creteForm" action="${pageContext.request.contextPath}/run/create.jsp" method="post">
            <span>
                Username
            </span>
            <span>
                <input id="username" type="text" name="username" required="true"/>
            </span>
            <span>
                First name
            </span>
            <span>
                <input id="firstName" type="text" name="firstName" required="true"/>
            </span>
            <span>
                Last name
            </span>
            <span> 
                <input id="lastName" type="text" name="lastName" required="true"/>
            </span>
            <span>
                Email
            </span>
            <span> 
                <input id="email" type="text" name="email" required="true"/>
            </span>
            <span>
                Password
            </span>
            <span>                
                <input id="password" type="password" name="password" required="true"/>
            </span>
            <span>
                <input type="submit" class="createbtn" value="Create"/>
            </span>
        </form>
        <form action="index.jsp" method="post" >
            <span>
                <input type="submit" value="Cancel" class="cancelbtn"/>
            </span>
        </form>
    </body>
</html>
