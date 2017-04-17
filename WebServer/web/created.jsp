<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
    <head>       
        <jsp:setProperty name="created" property="*" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account created for ${created.username}</title>       
    </head>
    <body>            
        <h1>
            Welcome ${created.lastName}!
        </h1>
        <p>Your account was successfully created</p>
        <p>Name: ${created.firstName} ${created.lastName}</p>
        <p>Username: ${created.username}</p>
        <p>Email: ${created.email}</p>    
        <form action="index.jsp" method="post">
            <input type="submit" value="Home"/>
        </form>
    </body>
</html>
