<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<jsp:useBean id="user" class="web.WebUser" scope="session" />
<jsp:setProperty name="user" property="*" />
<h1>Welcome ${user.username}<jsp:getProperty name="user" property="id" /></h1>