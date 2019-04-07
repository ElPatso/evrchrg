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
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.css"/>

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
            <span>Rent Charge Point</span>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">

                </ul>
            </div>
            <a href="${contextPath}/logout">Logout</a>
        </nav>

        <div class="centered" style="width: 30%">
            <label style="margin: 5px; font-size: 11px">request's status</label>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">status</label>
                    <span style="display: block">${info.status}</span>
                </div>
            </div>
            <label style="margin: 5px; font-size: 11px">general information</label>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">owner</label>
                    <span style="display: block">${info.owner}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">address</label>
                    <span style="display: block">${info.address}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">charger type</label>
                    <span id="type" style="display: block">${info.type}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">opening hours</label>
                    <span style="display: block">${info.openingHours}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">description</label>
                    <span style="display: block">${info.description}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">access instructions</label>
                    <span style="display: block">${info.instruction}</span>
                </div>
            </div>
            <input type="submit" value="Take me there" class="btn btn-primary"
                   style="width: 100%;"/>
            <label style="margin: 5px; font-size: 11px">charge rental details</label>

            <div>
                <div class="card right" style="margin: 0px; width: 45%">
                    <div style="padding: 5px; margin: 5px" class="car-body">
                        <label style="margin: 0px; font-size: 11px">leaving</label>
                        <span style="display: block">${info.leaving}</span>
                    </div>
                </div>
                <div class="card left" style="margin: 5px; width: 45%">
                    <div style="padding: 5px; margin: 5px" class="car-body">
                        <label style="margin: 0px; font-size: 11px">arriving</label>
                        <span style="display: block">${info.arriving}</span>
                    </div>
                </div>
                <div class="card" style="margin: 5px">
                    <div style="padding: 5px; margin: 5px" class="car-body">
                        <label style="margin: 0px; font-size: 11px">pricing</label>
                        <span style="display: block">${info.pricing}</span>
                    </div>
                </div>
            </div>
            <c:if test="${info.status == 'Approved'}">
                <a id="submit" type="submit" class="btn btn-primary" href="${contextPath}/feedback/${info.id}"
                   style="width: 100%; color: white">Leave feedback</a>
            </c:if>
        </div>

    </div>
</div>

</body>
</html>
