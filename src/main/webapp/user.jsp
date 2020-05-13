<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form ${requestScope.user}>
    Id User:
    ${requestScope.user.id}<br><br>
    Login:
    ${requestScope.user.name}<br><br>
    Password:
    ${requestScope.user.password}<br><br>
    Age:
    ${requestScope.user.age}<br>
</form>
</body>
</html>
