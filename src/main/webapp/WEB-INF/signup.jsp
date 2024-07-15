<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="path/to/your/css/styles.css">
</head>
<body>
<div class="container">
    <h1 class="text-center">Регистрация</h1>
    <form class="form-horizontal" action="signup" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="username">Имя пользователя:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Пароль:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Регистрация</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<%@include file="parts/footer.jsp"%>
