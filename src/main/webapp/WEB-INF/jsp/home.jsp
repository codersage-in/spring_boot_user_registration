<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <title>Codersage.in Home Page</title>
</head>

<h2>Codersage.in Home Page</h2>
<hr>

Welcome to the Codersage.in home page!
<br/>
<hr>
<!-- display user name and role -->
<p>
    <sec:authorize access="isAuthenticated()">
    User: <security:authentication property="principal.username" />
    <br><br>
    Role(s): <security:authentication property="principal.roles" />
    </sec:authorize>
</p>
<hr>
<p>
    <security:authorize access="hasRole('ADMIN')">
        <a href="${pageContext.request.contextPath}/admin/adminHome">Admin</a>
    </security:authorize>
    <br/>
    <security:authorize access="hasRole('INSTRUCTOR')">
        <a href="${pageContext.request.contextPath}/instructor/instructorHome">Guest</a>
    </security:authorize>
    <security:authorize access="hasRole('STUDENT')">
        <a href="${pageContext.request.contextPath}/student/studentHome">Guest</a>
    </security:authorize>
</p>
<hr>
<form:form action="${pageContext.request.contextPath}/logout"
           method="POST">
    <input type="submit" value="logout"/>
</form:form>
</body>

</html>