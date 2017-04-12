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
                <p:outputLabel for="lastName" value="Last name"/>
            </span>
            <span> 
                <p:inputText id="lastName" value="#{created.lastName}" required="true"/>
            </span>
            <span>
                <p:outputLabel for="email" value="Email"/>
            </span>
            <span> 
                <p:inputText id="email" value="#{created.email}" required="true"/>
            </span>
            <span>
                <p:outputLabel for="password" value="Password"/>
            </span>
            <span>
                <p:password id="password" value="#{created.password}" feedback="true" required="true"/>
            </span>
            <span>
                <p:commandButton value="Create account" action="#{created.create}" class="createbtn" update="creteForm"/>
            </span>
        </form>
    <h:form>           
        <span>
            <p:commandButton value="Cancel" action="index" class="cancelbtn"/>
        </span>
    </h:form>
</body>
</html>
