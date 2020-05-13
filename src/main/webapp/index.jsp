<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All users</title>
</head>

<body>
<div>
    <c:forEach items="${requestScope.users}" var="user">
        <c:out value="${user.name}"/>
        <c:out value="${user.password}"/>
        <c:out value="${user.age}"/><br>
        <form method="post">
            <button name="id" value="${user.id}" formaction="/admin/delete">Delete</button>
        </form>
        <form method="get">
            <button name="id" value="${user.id}" formaction="/admin/update">Update</button>
        </form>
    </c:forEach>
</div>
<form action="/admin/add" method="post">
    Role:<br>
    <input type="text" name="role"><br><br>
    Name:<br>
    <input type="text" name="name"><br><br>
    Password:<br>
    <input type="text" name="password"><br><br>
    Age:<br>
    <input type="number" name="age"><br>
    <button>Let's go...</button>
</form>

</body>
</html>
