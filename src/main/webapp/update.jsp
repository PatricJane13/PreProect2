<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
</head>

<body>
<form action="/admin/update" method="post" ${requestScope.user}>
    <input type="hidden" value="${requestScope.user.id}" name="id">
    NewLogin:<br>
    <input type="text" name="newName"><br><br>
    NewPassword:<br>
    <input type="text" name="newPassword"><br><br>
    NewAge:<br>
    <input type="number" name="newAge"><br>
    <button formmethod="post">Let's go update...</button>
</form>
</body>
</html>
