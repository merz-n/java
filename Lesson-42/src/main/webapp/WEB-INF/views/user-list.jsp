<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
    <h1>Список пользователей</h1>

    <table border="1" cellpadding="5">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th>Адреса</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>
                    <c:forEach var="address" items="${user.addresses}">
                        ${address.city}, ${address.street}, ${address.postal_code}<br>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br><a href="${pageContext.request.contextPath}/users/add">Добавить нового пользователя</a>
</body>
</html>
