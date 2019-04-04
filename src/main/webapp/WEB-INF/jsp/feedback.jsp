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
        function rate() {
            document.getElementById("rateButton").click();
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
            <span>Feedback</span>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">

                </ul>
            </div>
        </nav>
        <div class="centered" style="width: 30%">
            <H2>
                How would you rate your experience?
            </H2>

            <label>Star rating</label>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <form:form method="post" id="rateForm" modelAttribute="rating" action="feedback"
                               cssClass="starrating risingstar d-flex justify-content-center flex-row-reverse">
                    <form:radiobutton path="rate" id="star5" name="rating" value="5"
                                      onclick="rate()"/><label class="full"
                                                               for="star5"
                                                               title="Awesome - 5 stars"></label>
                    <form:radiobutton path="rate" id="star4" name="rating" value="4"
                                      onclick="rate()"/><label class="full"
                                                               for="star4"
                                                               title="Pretty good - 4 stars"></label>
                    <form:radiobutton path="rate" id="star3" name="rating" value="3"
                                      onclick="rate()"/><label class="full"
                                                               for="star3"
                                                               title="Meh - 3 stars"></label>
                    <form:radiobutton path="rate" id="star2" name="rating" value="2"
                                      onclick="rate()"/><label class="full"
                                                               for="star2"
                                                               title="Kinda bad - 2 stars"></label>
                    <form:radiobutton path="rate" id="star1" name="rating" value="1"
                                      onclick="rate()"/><label class="full"
                                                               for="star1"
                                                               title="Sucks big time - 1 star"></label>
                    <input type="submit" value="Rate" class="btn btn-success" id="rateButton"/>
                </div>
            </div>
            <div class="form-group" style="margin: 5px">
                <form:textarea cssClass="form-control" path="description" cols="45" rows="5"/>
                <input id="submit" type="submit" value="Submit" class="btn btn-primary" style="width: 100%; margin: 5px"/>
            </div>
            </form:form>

        </div>

    </div>

</div>
</div>

</body>
</html>
