<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />  
<jsp:useBean id="created" scope="session" class="web.WebUser" />
<jsp:setProperty name="created" property="*" />
<%
    boolean succ = created.create();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if (succ) {%>
        <title>Account created for ${created.username}</title> 
        <%} else {%>
        <title>Error creating account!</title> 
        <%}%>
    </head>
    <body>
        <%if (succ) {%>
        <h1>
            Welcome ${created.lastName}!
        </h1>
        <p>Your account was successfully created</p>
        <p>Name: ${created.firstName} ${created.lastName}</p>
        <p>Username: ${created.username}</p>
        <p>Email: ${created.email}</p>
        <form action="${pageContext.request.contextPath}/index.jsp" method="post">
            <input type="submit" value="Home"/>
        </form>
        <%} else {%>
        <h1>
            Unable to create account!
        </h1>
        <form action="${pageContext.request.contextPath}/create.jsp" method="post">
            <input type="submit" value="Retry"/>
        </form>
        <%}%>
    </body>
</html>