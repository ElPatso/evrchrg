<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:url value="/css" var="css"/>
<spring:url value="/js" var="js"/>
<spring:url value="/img" var="img"/>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css"/>
    <link href="${css}/sidebar.css" rel="stylesheet"/>
    <link href="${css}/main.css" rel="stylesheet"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
            crossorigin="anonymous"></script>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>


</head>
<body>

<div class="d-flex" id="wrapper">

    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">Evrecharge</div>
        <div class="list-group list-group-flush">

            <div class="col-2 collapse d-md-flex bg-light pt-2 min-vh-100" id="sidebar">
                <ul class="nav flex-column flex-nowrap">
                    <li class="nav-item"><a class="nav-link" href="#"></a></li>
                    <li class="nav-item">
                        <a class="collapsed" href="#submenu1" data-toggle="collapse"
                           data-target="#submenu1">Requests</a>

                        <div style="width: 237px" class="collapse" id="submenu1" aria-expanded="false">
                            <ul class="flex-column pl-2 nav">
                                <li class="nav-item"><a class="nav-link py-0" href="${contextPath}/">Sent - My EV</a></li>
                                <li class="nav-item"><a class="nav-link py-0" href="${contextPath}/received">Received - My Charge Point</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>

        </div>
    </div>
    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <span>Sent - My EV</span>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">

                </ul>
            </div>
            <a href="${contextPath}/logout">Logout</a>
        </nav>
        <c:forEach items="${requests}" var="request">
            <div class="centered">
                <div class="card" style="margin: 5px; display: inline-block; width: 50%">
                    <div style="float: left; width: 65%; padding: 1%">
                        <span id="type"><a href="${contextPath}/${request.url}/${request.id}">${request.user}</a></span>
                        <label for="type" style="display: block; font-size: 11px">${request.from}
                            - ${request.to}</label>
                    </div>
                    <div style="float: right; width: 35%; padding: 3%; text-align: right;">
                        <span>${request.status}</span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
