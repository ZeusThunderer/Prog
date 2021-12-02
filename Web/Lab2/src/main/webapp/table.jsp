<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Check result</title>
    <link rel="stylesheet" href="main.css">
</head>

<body>

<header id="head">
    <p>Закиров Тимур P3211 Вариант:12010</p></span>
</header>
<div>
<p id="enter-text">Check results</p>
<jsp:include page="row.jsp" />
</div>
<form method="get" action="ControllerServlet">
    <button id="button" class="back_button" type="submit">Take me back.</button>
</form>
</body>
</html>
