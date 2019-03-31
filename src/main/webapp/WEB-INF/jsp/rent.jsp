<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/css" var="css"/>
<spring:url value="/img" var="img"/>
<html>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>

    <link href="${css}/sidebar.css" rel="stylesheet"/>
    <link href="${css}/main.css" rel="stylesheet"/>

</head>
<body>

<div class="d-flex" id="wrapper">

    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">Start Bootstrap</div>
        <div class="list-group list-group-flush">
            <a href="#" class="list-group-item list-group-item-action bg-light">Dashboard</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Shortcuts</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Overview</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Events</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Profile</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Status</a>
        </div>
    </div>
    <div id="page-content-wrapper">

        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <span>Rent Charge Point</span>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">

                </ul>
            </div>
        </nav>

        <div style="width: 50%; padding: 10px">
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">A Basic Panel</div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">A Basic Panel</div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">A Basic Panel</div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">A Basic Panel</div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">A Basic Panel</div>
            </div>

            <div id="demo" class="carousel slide" data-ride="carousel">

                <!-- Indicators -->
                <ul class="carousel-indicators">
                    <li style="background-color: blue" data-target="#demo" data-slide-to="0"></li>
                    <li style="background-color: blue" data-target="#demo" data-slide-to="1"></li>
                    <li style="background-color: blue" data-target="#demo" data-slide-to="2"></li>
                </ul>

                <!-- The slideshow -->
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="${img}/logo.jpg" alt="Los Angeles">
                    </div>
                    <div class="carousel-item">
                        <img src="${img}/logo.jpg" alt="Chicago">
                    </div>
                    <div class="carousel-item">
                        <img src="${img}/logo.jpg" alt="New York">
                    </div>
                </div>
            </div>
            <select id="select" name="source" onchange="">
                <option value="rss">RSS LINK</option>
                <option value="other">OTHER LINK</option>
            </select>
            <select name="source" onchange="">
                <option value="rss">RSS LINK</option>
                <option value="other">OTHER LINK</option>
            </select>
        </div>

    </div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#select').val("OTHER LINK");
        $('#select').select().trigger('change');
    })
</script>
</div>
</body>
</html>
