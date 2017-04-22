<jsp:directive.page pageEncoding="UTF-8"/>
<jsp:useBean id="user" class="web.WebUser" scope="session" />
<jsp:setProperty name="user" property="*" />
<div id="form">
    <div class="imgcontainer">
        <img src="resources/csgobet.png" class="imagine "/>
    </div>
    <form action="${pageContext.request.contextPath}/run/log.jsp" method="post" >
        <div class="container">
            Username: <input type="text" name="username" required />
            Password: <input type="password" name="password" required />
            <input type="submit" value="Login"/>
        </div>
    </form>
    <form action="create.jsp" class="container" style="background-color:#f1f1f1">
        <input type="submit" value="Create"/>
        <input type="submit" value="Cancel"/>
        <span class="psw">Forgot <a>password</a></span>
    </form>
</div>