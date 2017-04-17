<jsp:setProperty name="user" property="*" />
<jsp:directive.page import="web.WebUser" />
<%
    WebUser user = (WebUser) request.getSession().getAttribute("user");
    user.setId(9);
%>
<jsp:forward page="/index.jsp" />
