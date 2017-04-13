<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/createstyle.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="created" scope="request" class="web.WebUser" />
        <jsp:setProperty name="created" property="*" />
        <title>Create account</title>
    </head>
    <body>
        <div id="left"></div>
        <div id="right"></div>
        <div id="top"></div>
        <div id="bottom"></div>
        <form id="creteForm" method="post">
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
                <input id="password" type="text" name="password" required="true"/>
            </span>
            <span>
                <input type="submit" class="createbtn" value="Create"/>
            </span>
        </form>
    <h:form>           
        <span>
            <input type="submit" value="Cancel" class="cancelbtn"/>
        </span>
    </h:form>
</body>
</html>
