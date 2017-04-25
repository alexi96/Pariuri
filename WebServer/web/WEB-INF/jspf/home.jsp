<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<jsp:useBean id="user" class="web.WebUser" scope="session" />
<jsp:setProperty name="user" property="*" />
<h1>Welcome ${user.username}</h1>
<form action="${pageContext.request.contextPath}/run/logout.jsp" method="post">
    <input type="submit" value="Log out">
</form>

<form action="${pageContext.request.contextPath}/createTicket.jsp" method="post">
    <input type="submit" value="Create ticket" />
</form>