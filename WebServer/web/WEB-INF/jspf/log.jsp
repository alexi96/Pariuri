<jsp:directive.page pageEncoding="UTF-8"/>
<jsp:useBean id="user" class="web.WebUser" scope="session" />
<jsp:setProperty name="user" property="*" />
<div id="form">
    <div class="imgcontainer">
        <img src="resources/csgobet.png" class="imagine "/>
    </div>
    <form action="${pageContext.request.contextPath}/run/log" method="post" >
        <div class="container">
            <p style="color: red"><%=user.isError() ? "Invalid username or password!" : ""%></p>
            Username: <input type="text" name="username" required />
            Password: <input type="password" name="password" required />
            <input type="submit" value="Login"/>
        </div>
    </form>
    <form action="create.jsp" class="container" style="background-color:#f1f1f1">
        <input type="submit" value="Create"/>
        <input type="submit" value="Cancel"/>
        <span class="psw"><a href="${pageContext.request.contextPath}/recovery.jsp">Forgot password</a></span>
    </form>
</div>