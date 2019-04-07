<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <script type="text/javascript">

        $(function () {
            $('#datetimepicker2').datetimepicker({
                locale: 'ua'
            });
        });

        function selectChange() {
            var type = $('#type').text();
            var e = document.getElementById("dur");
            var strUser = e.options[e.selectedIndex].value;

            $.ajax({
                url: "/price",
                data: {
                    "type": type,
                    "duration": strUser
                },
                type: "GET",
                contentType: 'application/json',

                success: function (data) {
                    $('#price').text(data.currencySymbol + data.price);
                },
                error: function (ts) {
                    alert(ts.responseText)
                }

            });
        }

        function getCartInfo() {
            $.ajax({
                url: "/credit-card-info",
                contentType: 'application/json',
                type: 'GET',
                success: function (dataofconfirm) {
                    $('#cardNumber').val(dataofconfirm.cardNumber);
                    $('#expMonth').val(dataofconfirm.expMonth);
                    $('#expYear').val(dataofconfirm.expYear);
                    $('#cvc').val(dataofconfirm.cvc);
                }
            });
        }

        function book(token) {
            var e = document.getElementById("dur");
            var strUser = e.options[e.selectedIndex].value;
            var time = $('#datetimepicker').val();
            var body = {
                "id": ${point.id},
                "time": time,
                "duration": strUser,
                "token": token
            };
            $.ajax({
                url: "/book",
                data: JSON.stringify(body),
                contentType: 'application/json',
                type: 'POST',
                success: function (dataofconfirm) {
                    window.location.href = dataofconfirm;
                }
            });
        }

        Stripe.setPublishableKey('pk_test_P2ryZyvZrdRTKLREwV3eClqp00rRrpJvpK');

        $(function () {
            var $form = $('#payment-form');
            $form.submit(function (event) {
                $form.find('.submit').prop('disabled', true);

                Stripe.card.createToken($form, stripeResponseHandler);

                return false;
            });
        });

        function stripeResponseHandler(status, response) {
            var $form = $('#payment-form');

            if (response.error) {
                $form.find('.payment-errors').text(response.error.message);
                $form.find('.submit').prop('disabled', false);

            } else {
                var token = response.id;
                book(token);
            }
        };
    </script>
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
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">owner</label>
                    <span style="display: block">${point.owner}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">post code</label>
                    <span style="display: block">${point.postCode}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">charger type</label>
                    <span id="type" style="display: block">${point.type}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">availability</label>
                    <span style="display: block">${point.availableTime}</span>
                </div>
            </div>
            <div class="card" style="margin: 5px">
                <div style="padding: 5px; margin: 5px" class="car-body">
                    <label style="margin: 0px; font-size: 11px">description</label>
                    <span style="display: block">${point.description}</span>
                </div>
            </div>

            <div id="demo" class="carousel slide" data-ride="carousel">

                <ul class="carousel-indicators">
                    <li style="background-color: blue" data-target="#demo" data-slide-to="0"></li>
                    <li style="background-color: blue" data-target="#demo" data-slide-to="1"></li>
                    <li style="background-color: blue" data-target="#demo" data-slide-to="2"></li>
                </ul>

                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="${img}/logo.jpg" alt="">
                    </div>
                    <div class="carousel-item">
                        <img src="${img}/logo.jpg" alt="">
                    </div>
                    <div class="carousel-item">
                        <img src="${img}/logo.jpg" alt="">
                    </div>
                </div>
            </div>
            <div>
                <select style="width: 30%; margin-top: 10px" onchange="selectChange()"
                        id="dur" name="source" class="right browser-default custom-select">
                    <option value="1">1 hour</option>
                    <option value="2">2 hours</option>
                    <option value="3">3 hours</option>
                    <option value="4">4 hours</option>
                    <option value="5">5 hours</option>
                </select>

                <div class="col-sm-6" style="width: 100%; padding: 5px">
                    <div class="input-group date" id="datetimepicker2" data-target-input="nearest">
                        <input id="datetimepicker" type="text" class="left form-control datetimepicker-input"
                               data-target="#datetimepicker2"
                               data-toggle="datetimepicker"/>
                    </div>

                </div>
                <div class="card" style="height: 36px; width: 100%; margin: 5px">
                    <div style="padding: 5px; margin: 5px" class="car-body"><span id="price"></span></div>
                </div>
            </div>
            <input onclick="getCartInfo()" id="submit" type="submit" value="Submit" class="btn btn-primary"
                   style="width: 100%;"
                   data-toggle="modal" data-target="#exampleModalCenter"/>
        </div>

    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form action="/your-charge-code" method="POST" id="payment-form">

                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Card info</h5>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label for="cardNumber" class="control-label mb-1">Card number</label>
                        <input id="cardNumber" readonly name="cc-number" type="tel" data-stripe="number"
                               class="form-control cc-number identified visa"
                               required="" pattern="[0-9]{16}"/>
                        <span class="invalid-feedback">Enter a valid 16 digit card number</span>
                    </div>
                    <div class="row">
                        <div class="col-6">
                                <label for="expYear" class="control-label mb-1">Expiration</label>
                                <div style="width: 105px">
                                    <input style="width: 50px" id="expYear" readonly name="cc-exp" type="tel"
                                           data-stripe="exp_year"
                                           class="right form-control cc-exp" required
                                           placeholder="YY" autocomplete="cc-exp">
                                    <input style="width: 50px" id="expMonth" readonly name="cc-exp" type="tel"
                                           data-stripe="exp_month"
                                           class="left form-control cc-exp" required
                                           placeholder="MM" autocomplete="cc-exp">
                                    <span class="invalid-feedback">Enter the expiration date</span>
                                </div>

                        </div>
                        <div class="col-6">
                            <label for="cvc" class="control-label mb-1">Security code</label>
                            <div class="input-group">
                                <input id="cvc" readonly name="x_card_code" type="tel" data-stripe="cvc"
                                       class="form-control cc-cvc"
                                       required
                                       autocomplete="off"/>
                                <span class="invalid-feedback order-last">Enter the 3-digit code on back</span>
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <span class="fa fa-question-circle fa-lg" data-toggle="popover"
                                              data-container="body" data-html="true" data-title="Security Code"
                                              data-content="<div class='text-center one-card'>The 3 digit code on back of the card..<div class='visa-mc-cvc-preview'></div></div>"
                                              data-trigger="hover"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Submit Payment</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
