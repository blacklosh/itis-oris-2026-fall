<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <h1><c:out value="${param.title}" default="Учебный проект"/></h1>
    <nav>
        <a href="${pageContext.request.contextPath}/users">Пользователи</a>
        <a href="${pageContext.request.contextPath}/dashboard">Панель</a>
    </nav>
</header>
<hr>
