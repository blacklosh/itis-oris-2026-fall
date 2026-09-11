<%@ page import="ru.itis.model.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1 style="color: red">Hello world!</h1>

<h2>Это наша страничка, только открытая в JSP</h2>

<p style="font-size: 40px">
<%=request.getAttribute("str")%>
</p>

<h3>Все активные пользователи на ресурсе:</h3>

<%
    for (UserModel current : (List<UserModel>) request.getAttribute("users")) {
        %>
            username = <%=current.getName()%>,
            email = <%=current.getEmail()%> <br/>
        <%
    }
%>

</body>
</html>
