<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageTitle}"/></title>
</head>
<body>
<c:import url="/jsp/fragments/header.jsp">
    <c:param name="title" value="${pageTitle}"/>
</c:import>

<main>
    <p>Добро пожаловать, <c:out value="${username}"/>!</p>

    <h2>Уведомления</h2>
    <ul>
        <c:forEach var="notification" items="${notifications}">
            <li><c:out value="${notification}"/></li>
        </c:forEach>
    </ul>
</main>

<c:import url="/jsp/fragments/footer.jsp"/>
</body>
</html>
