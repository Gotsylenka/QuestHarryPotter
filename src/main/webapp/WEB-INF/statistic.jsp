<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<c:import url="parts/header.jsp"/>

<div class="container">
    <h2>Статистика пользователей</h2>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">Логин</th>
            <th scope="col">Всего</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <c:set var="stat" value="${userStatisticMap[user.id]}"/>
            <tr>
                <td>${user.login}</td>
                <td>${stat != null ? stat.playedGames : "Нет данных"}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<c:import url="parts/footer.jsp"/>p"/>