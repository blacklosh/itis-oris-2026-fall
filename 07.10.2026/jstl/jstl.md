# Зачем нужен JSTL

## 1. Убирает Java-код из JSP

Было:

```jsp
<%
List<User> users = (List<User>) request.getAttribute("users");
for (User u : users) {
    if (u.isActive()) {
        out.print("<li>" + u.getName() + "</li>");
    }
}
%>

```
Стало:

```jsp
<c:forEach var="u" items="${users}">
    <c:if test="${u.active}">
        <li><c:out value="${u.name}"/></li>
    </c:if>
</c:forEach>

```

## 2. Безопаснее

<c:out> по умолчанию экранирует XML/HTML, что снижает риск XSS. fn:escapeXml делает то же для выражений.

## 3. Читаемее и поддерживаемее

Шаблон выглядит как HTML с тегами, а не как смесь Java и HTML.

## 4. MVC

Контроллер готовит данные, JSP+JSTL только отображает. Логика не растекается по страницам.

## 5. Переносимость

JSTL — стандарт Java EE / Jakarta EE. Если не использовать самописные скриптлеты, страницы легче переносить между контейнерами.

## Подключение JSTL

### Старые javax-версии (Java EE 8 и ниже)

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

```
Maven:

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>

```

### Jakarta EE 9+

```jsp
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<%@ taglib prefix="sql" uri="jakarta.tags.sql" %>

```
Maven:

```xml
<dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
    <version>3.0.1</version>
</dependency>

```

## Core-теги (c)

### c:out — безопасный вывод

```jsp
<c:out value="${user.name}" default="Гость" escapeXml="true"/>

```
Если user.name == null, выведется Гость. HTML-теги будут экранированы.

### c:set — установка переменной или свойства бина

```jsp
<c:set var="title" value="Список пользователей" scope="request"/>
<c:set target="${user}" property="name" value="Иван"/>

```

### c:remove — удаление переменной из scope

```jsp
<c:remove var="title" scope="request"/>

```

### c:catch — перехват исключения

```jsp
<c:catch var="ex">
    <c:set target="${user}" property="age" value="${param.age}"/>
</c:catch>

<c:if test="${not empty ex}">
    Ошибка: ${ex.message}
</c:if>

```

### c:if — условие без else

```jsp
<c:if test="${empty users}">
    Нет пользователей
</c:if>

<c:if test="${user.role == 'ADMIN'}" var="isAdmin" scope="page"/>
<c:if test="${isAdmin}">
    Админ-панель
</c:if>

```

### c:choose, c:when, c:otherwise — if / else if / else

```jsp
<c:choose>
    <c:when test="${user.role == 'ADMIN'}">
        Администратор
    </c:when>
    <c:when test="${user.role == 'USER'}">
        Пользователь
    </c:when>
    <c:otherwise>
        Гость
    </c:otherwise>
</c:choose>

```

### c:forEach — циклы

По коллекции:

```jsp
<c:forEach var="user" items="${users}" varStatus="st">
    <tr class="${st.index % 2 == 0 ? 'even' : 'odd'}">
        <td>${st.count}</td>
        <td><c:out value="${user.name}"/></td>
        <td>${st.first ? 'Первый' : ''}</td>
        <td>${st.last ? 'Последний' : ''}</td>
    </tr>
</c:forEach>

```
По диапазону:

```jsp
<c:forEach var="i" begin="1" end="10" step="2">
    ${i}
</c:forEach>

```
По Map:

```jsp
<c:forEach var="entry" items="${map}">
    ${entry.key} = ${entry.value}
</c:forEach>

```

### c:forTokens — разбиение строки

```jsp
<c:forTokens items="Java,JSP,JSTL,Servlet" delims="," var="item">
    <li>${item}</li>
</c:forTokens>

```

### c:import — включение ресурса

```jsp
<c:import url="/header.jsp"/>

<c:import url="https://example.com/api/data" var="data" charEncoding="UTF-8"/>
${data}

```
С параметрами:

```jsp
<c:import url="/search">
    <c:param name="q" value="${param.q}"/>
</c:import>

```

### c:url — корректный URL с параметрами и jsessionid

```jsp
<c:url var="searchUrl" value="/search">
    <c:param name="q" value="${param.q}"/>
    <c:param name="page" value="1"/>
</c:url>

<a href="${searchUrl}">Поиск</a>

```

### c:redirect — редирект

```jsp
<c:redirect url="/login">
    <c:param name="error" value="sessionExpired"/>
</c:redirect>

```

### c:param — параметр для c:url, c:redirect, c:import

```jsp
<c:url value="/user">
    <c:param name="id" value="${user.id}"/>
</c:url>

```

## Форматирование и i18n (fmt)

### fmt:setLocale — локаль

```jsp
<fmt:setLocale value="ru_RU"/>

```

### fmt:requestEncoding — кодировка запроса

```jsp
<fmt:requestEncoding value="UTF-8"/>

```

### fmt:setBundle, fmt:bundle, fmt:message, fmt:param — локализация

messages_ru.properties:

```properties
hello=Привет, {0}!

```
JSP:

```jsp
<fmt:setBundle basename="messages" var="msg"/>

<fmt:message key="hello" bundle="${msg}">
    <fmt:param value="${user.name}"/>
</fmt:message>

```

### fmt:formatNumber — числа, валюты, проценты

```jsp
<fmt:formatNumber value="${price}" type="currency" currencyCode="RUB"/>
<fmt:formatNumber value="${percent}" type="percent" maxFractionDigits="2"/>
<fmt:formatNumber value="${num}" pattern="#,##0.00"/>

```

### fmt:parseNumber — парсинг числа

```jsp
<fmt:parseNumber var="n" value="1 234,56" type="number" pattern="# ##0,00"/>

```

### fmt:formatDate — дата/время

```jsp
<fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="short"/>
<fmt:formatDate value="${date}" pattern="dd.MM.yyyy HH:mm"/>

```

### fmt:parseDate — парсинг даты

```jsp
<fmt:parseDate var="d" value="31.12.2025" pattern="dd.MM.yyyy"/>

```

### fmt:setTimeZone, fmt:timeZone — часовой пояс

```jsp
<fmt:setTimeZone value="Europe/Moscow"/>

<fmt:timeZone value="GMT+3">
    <fmt:formatDate value="${now}" pattern="HH:mm"/>
</fmt:timeZone>

```

## Функции строк (fn)

```jsp
${fn:length(users)}
${fn:toUpperCase(user.name)}
${fn:toLowerCase(user.name)}
${fn:trim(user.name)}
${fn:contains(user.name, 'admin')}
${fn:containsIgnoreCase(user.name, 'ADMIN')}
${fn:startsWith(user.name, 'A')}
${fn:endsWith(user.name, 'ov')}
${fn:indexOf(user.name, 'a')}
${fn:substring(user.name, 0, 3)}
${fn:substringBefore(user.email, '@')}
${fn:substringAfter(user.email, '@')}
${fn:replace(user.name, ' ', '_')}
${fn:escapeXml(param.comment)}

```
Разбить и склеить:

```jsp
<c:set var="parts" value="${fn:split(csv, ',')}"/>
${fn:join(parts, '; ')}

```

## XML-теги (x)

Используются для XPath-обработки XML.

```jsp
<x:parse xml="${xmlString}" var="doc"/>

<x:out select="$doc/books/book[1]/title"/>

<x:forEach select="$doc/books/book" var="book">
    <x:out select="$book/title"/>
</x:forEach>

<x:if select="$doc/books/book">
    Книги есть
</x:if>

<x:choose>
    <x:when select="$doc/books/book[@id='1']">
        Найдена книга 1
    </x:when>
    <x:otherwise>
        Не найдена
    </x:otherwise>
</x:choose>

<x:set select="$doc/books/book[1]/title" var="title"/>
${title}

<x:transform doc="${doc}" xslt="${xsltDoc}">
    <x:param name="mode" value="short"/>
</x:transform>

```

## SQL-теги (sql)

Их обычно не рекомендуют использовать в JSP, потому что это смешивает доступ к БД и представление. Но они существуют.

```jsp
<sql:setDataSource var="ds"
    driver="org.postgresql.Driver"
    url="jdbc:postgresql://localhost/mydb"
    user="user"
    password="pass"/>

<sql:query var="users" dataSource="${ds}">
    SELECT * FROM users WHERE age > ?
    <sql:param value="${minAge}"/>
</sql:query>

<c:forEach var="row" items="${users.rows}">
    ${row.name} — ${row.age}<br/>
</c:forEach>

<sql:update dataSource="${ds}">
    UPDATE users SET active = true WHERE id = ?
    <sql:param value="${userId}"/>
</sql:update>

<sql:transaction dataSource="${ds}">
    <sql:update>...</sql:update>
    <sql:update>...</sql:update>
</sql:transaction>

```
С датой:

```jsp
<sql:dateParam value="${date}" type="timestamp"/>

```

## Кастомные теги

JSTL — это стандартные теги. Если нужен свой UI-компонент, можно сделать .tag-файл.

/WEB-INF/tags/userCard.tag:

```jsp
<%@ tag body-content="empty" %>
<%@ attribute name="user" required="true" type="com.example.User" %>
<div class="user-card">
    <c:out value="${user.name}"/>
</div>

```
Использование:

```jsp
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:userCard user="${user}"/>

```

## Когда JSTL недостаточно

JSTL не заменяет контроллер и бизнес-логику. Если появляются сложные вычисления, доступ к БД, транзакции, проверки прав — это должно быть в Servlet, Spring MVC, EJB, сервисном слое. JSTL — только для отображения.

## Итог

JSTL нужен, чтобы:

- не писать Java-код в JSP;
- использовать готовые теги для условий, циклов, URL, i18n, форматирования;
- безопаснее выводить данные;
- держать MVC-разделение;
- упростить поддержку JSP.

Основные пространства имён:

- c — логика страницы;
- fmt — форматирование и локализация;
- fn — строковые функции;
- x — XML/XPath;
- sql — SQL, но в современном коде лучше не использовать в JSP.
