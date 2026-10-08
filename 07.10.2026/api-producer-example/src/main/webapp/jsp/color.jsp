<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: <%=request.getAttribute("color")%>">
  <h1>
    Привет! Эта страница умеет менять цвет
  </h1>

    <form action="/color" method="post">
        <input type="text" name="selected-color"/>
        <input type="submit" value="Save"/>
    </form>
</body>
</html>
