<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>
    <title>Добавить пользователя</title>
</head>
<body>
    <h1>Добавить пользователя</h1>

    <form action="${pageContext.request.contextPath}/users/addUser" method="POST">
        Имя:<br>
        <input type="text" name="name" required/><br>
        Email:<br>
        <input type="email" name="email" required/><br><br>
        <input type="submit" value="Добавить"/>
    </form>

    <br><a href="${pageContext.request.contextPath}/users">Вернуться к списку</a>
</body>
</html>
