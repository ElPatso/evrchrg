<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/css" var="css"/>
<spring:url value="/js" var="js"/>
<spring:url value="/img" var="img"/>
<html>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css"/>
    <link href="${css}/sidebar.css" rel="stylesheet"/>
    <link href="${css}/main.css" rel="stylesheet"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>

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
            var time = $('#datetimepicker5').val();
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

        <div class="centered" style="width: 30%">
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
                <div style="padding: 5px; margin: 5px" class="car-body">${point.availableTime}</div>
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
                <select style="width: 200px" onchange="selectChange()"
                        id="dur" name="source" class="right browser-default custom-select">
                    <option value="3">3 hours</option>
                    <option value="4">4 hours</option>
                </select>
                <div class="col-sm-6" style="padding-left: 5px">
                    <input style="width: 200px; margin: 0px"
                           type="text" class="left form-control datetimepicker-input" id="datetimepicker5"
                           data-toggle="datetimepicker" data-target="#datetimepicker5"/>
                </div>
                <div class="card" style="height: 36px; width: 200px; margin: 5px">
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
                            <div class="form-group">
                                <label for="expMonth" class="control-label mb-1">Expiration</label>
                                <input id="expMonth" readonly name="cc-exp" type="tel" data-stripe="exp_month"
                                       class="form-control cc-exp" required
                                       placeholder="MM" autocomplete="cc-exp">
                                <input id="expYear" readonly name="cc-exp" type="tel" data-stripe="exp_year"
                                       class="form-control cc-exp" required
                                       placeholder="YY" autocomplete="cc-exp">
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
