<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Информация о пользователе</title>
</head>
<body>
    <h1>Пользователь</h1>

    <p><strong>Имя:</strong> ${user.name}</p>
    <p><strong>Email:</strong> ${user.email}</p>

    <h2>Адреса:</h2>
    <ul>
        <c:forEach var="address" items="${user.addresses}">
            <li>${address.city}, ${address.street}, ${address.postal_code}</li>
        </c:forEach>
    </ul>

    <a href="/users">Назад к списку</a>
</body>
</html>