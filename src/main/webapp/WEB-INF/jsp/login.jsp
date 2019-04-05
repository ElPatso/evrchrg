<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/css/main.css" var="springCss"/>
<spring:url value="/img" var="img"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.2.1/css/bootstrap.min.css"/>
    <link href="${springCss}" rel="stylesheet"/>
</head>
<body>
<form class="modal-content animate" method="POST">
    <div class="form">
        <div class="logo">
            <img src="${img}/logo.jpg">
        </div>
        <input name="username" type="text" placeholder="Emal"
               autofocus="true" required/>

        <input type="password" placeholder="Enter Password" name="password" required>
        <a style="color: dodgerblue">Forgot Password?</a>

        <button type="submit" style="margin-top: 20px" class="btn btn-primary">Login</button>
    </div>
</form>
</body>
</html>