<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Welcome to chat page!</h1>
    <h2>You logged in as <%=request.getAttribute("username")%></h2>


    Chat is currently unavailable... :(

    <hr>

    <a href="/logout">Logout</a>
</body>
</html>
