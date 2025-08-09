<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Информация о пользователе</title>
</head>
<body>
    <h2>Детали пользователя</h2>
    <p><strong>ID:</strong> ${user.id}</p>
    <p><strong>Имя:</strong> ${user.name}</p>
    <p><strong>Email:</strong> ${user.email}</p>
    <p><strong>Password:</strong> ${user.password}</p>
    <p><strong>Активен:</strong> <c:choose>
        <c:when test="${user.enabled}">Да</c:when>
        <c:otherwise>Нет</c:otherwise>
    </c:choose></p>

    <h3>Роли:</h3>
    <ul>
        <c:forEach var="authority" items="${user.authorities}">
            <li>${authority.authority}</li>
        </c:forEach>
    </ul>

    <h3>Адреса:</h3>
    <c:forEach var="address" items="${user.addresses}">
        <p><strong>Улица:</strong> ${address.street}</p>
        <p><strong>Город:</strong> ${address.city}</p>
        <p><strong>Почтовый индекс:</strong> ${address.postal_code}</p>
    </c:forEach>

    <br/>
    <a href="../users">Назад к списку пользователей</a>
</body>
</html>