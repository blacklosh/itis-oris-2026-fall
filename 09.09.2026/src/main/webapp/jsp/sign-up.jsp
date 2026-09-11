<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1 style="color: red">Sign up page</h1>

<form action="${pageContext.request.contextPath}/sign-up" method="post">

    Email: <input type="email" name="email"/> <br/>
    Username: <input type="text" name="username"/> <br/>
    <input type="submit" value="Sign Up!"/>

</form>

</body>
</html>
