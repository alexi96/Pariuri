<jsp:directive.page import="web.WebUser" />
<jsp:useBean id="user" class="web.WebUser" scope="session" />
<jsp:setProperty name="user" property="*" />
<%
    user.setId(9);
    ///TO DO
%>
<jsp:forward page="/index.jsp" />
