<jsp:useBean id="user" class="web.WebUser" scope="session" >
    <jsp:setProperty name="user" property="*" />
</jsp:useBean>
<%
    user.log();
%>