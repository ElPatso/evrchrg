<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/css" var="css"/>
<spring:url value="/js" var="js"/>
<spring:url value="/img" var="img"/>
<html>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>

    <link href="${css}/sidebar.css" rel="stylesheet"/>
    <link href="${css}/main.css" rel="stylesheet"/>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css"/>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker5').datetimepicker({
                format: 'H:m',
                disabledHours: [0, 1, 2, 3, 4, 5, 6, 20, 21, 22, 23, 24]
            });
        });
        function selectChange() {
            var type = $('#type').text();
            var e = document.getElementById("dur");
            var strUser = e.options[e.selectedIndex].value;

            $.ajax({
                url : "/price",
                data: {
                    "type" : type,
                    "duration": strUser
                },
                type: "GET",
                contentType: 'application/json',

                success: function(data) {
                    console.log(data);
                    $('#price').text(data);
                },
                error: function(ts) { alert(ts.responseText) }

            });
        }
    </script>
</head>
<body>

<div class="d-flex" id="wrapper">

    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">Evrecharge</div>
        <div class="list-group list-group-flush">
            <a href="#" class="list-group-item list-group-item-action bg-light">Rent charge point</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Requests</a>
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
                <div style="padding: 5px; margin: 5px" class="car-body">${point.owner}</div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">${point.postCode}</div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body"><span id="type">${point.type}</span></div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">A Basic Panel</div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">${point.description}</div>
            </div>

            <div id="demo" class="carousel slide" data-ride="carousel">

                <ul class="carousel-indicators">
                    <li style="background-color: blue" data-target="#demo" data-slide-to="0"></li>
                    <li style="background-color: blue" data-target="#demo" data-slide-to="1"></li>
                    <li style="background-color: blue" data-target="#demo" data-slide-to="2"></li>
                </ul>

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
            <form:form method="post" action="/request" modelAttribute="point">
                <div class="col-sm-6">
                    <form:input path="time"
                                type="text" class="form-control datetimepicker-input" id="datetimepicker5"
                           data-toggle="datetimepicker" data-target="#datetimepicker5"/>
                </div>
                <form:select onchange="selectChange()"
                        id="dur" path="duration" name="source" class="browser-default custom-select">
                    <option value="RSS LINK">RSS LINK</option>
                    <option value="OTHER LINK">OTHER LINK</option>
                </form:select>
                <input id="submit" type="submit" value="Submit"/>
            </form:form>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body"><span id="price"></span></div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
