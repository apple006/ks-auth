<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<a href="/logout">GET logout</a>
<br />
<form action="/sys/doLogout" method="post">
	<input type="hidden" name="${ _csrf.parameterName}" value="${ _csrf.token}" />
	<input type="submit" value="POST Logout"/>
</form>
</body>
</html>