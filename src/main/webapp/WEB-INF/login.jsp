<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp"%>

<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="selectUser">Имя пользователя:</label>
    <select id="selectUser" name="selectUser">
        <c:forEach var="user" items="${users}">
            <option value="${user.id}">${user.login}</option>
        </c:forEach>
    </select>
    <label for="passwordInput">Пароль:</label>
    <input type="password" id="passwordInput" name="passwordInput" required>

    <button id="loginButton"
            name="clickedButton"
            value="loginButton"
            class="btn btn-success">
        Войти</button>
    <button id="signButton"
            name="clickedButton"
            value="signButton"
            class="btn btn-primary">
        Зарегистрироваться</button>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
</form>
</body>
</html>
<c:import url="parts/footer.jsp"/>
