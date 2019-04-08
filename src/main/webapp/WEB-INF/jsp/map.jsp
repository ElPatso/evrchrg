<html>
<head>
    <title>Geolocation</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 425px;
        }

        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="map"></div>
<script>

    function initMap() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                var map = new google.maps.Map(document.getElementById('map'), {
                    center: pos,
                    zoom: 12
                });
                var marker;
                marker = new google.maps.Marker({
                    position: pos,
                    map: map,
                    title: 'Hello World!'
                });
                var bangalore = { lat: 49.80882145, lng: 24.0451126448625 };
                marker = new google.maps.Marker({
                    position: bangalore,
                    map: map,
                    title: 'Hello World!2'
                });

            });
        }
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=KEY&callback=initMap">
</script>
</body>
</html>