<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Пользователи</title>
</head>
<body>
<h1>Пользователи</h1>

<c:choose>
    <c:when test="${empty users}">
        <p>Пользователи не найдены.</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="user" items="${users}" varStatus="status">
                <c:if test="${user.active or showInactive}">
                    <li>
                        ${status.count}. <c:out value="${user.name}"/>

                        <c:choose>
                            <c:when test="${user.role == 'ADMIN'}">
                                <strong>(администратор)</strong>
                            </c:when>
                            <c:otherwise>
                                (пользователь)
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${not user.active}">
                            — неактивен
                        </c:if>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

<c:url var="toggleUrl" value="/users">
    <c:if test="${not showInactive}">
        <c:param name="all" value="true"/>
    </c:if>
</c:url>
<a href="${toggleUrl}">
    <c:choose>
        <c:when test="${showInactive}">Скрыть неактивных</c:when>
        <c:otherwise>Показать всех</c:otherwise>
    </c:choose>
</a>

<p><a href="${pageContext.request.contextPath}/dashboard">Открыть панель</a></p>
</body>
</html>
