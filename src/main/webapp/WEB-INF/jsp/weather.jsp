<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Weather informer</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600"/>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css"/>
    <!-- Magnific Popup core CSS file -->
    <link rel="stylesheet" href="../../resources/css/magnific-popup.css"/>
    <link rel="stylesheet" href="../../resources/css/templatemo-style.css"/>
    <!--
    Verticard Template
    https://templatemo.com/tm-533-verticard

    Template re-distribution is NOT allowed on any kind of download website. Thank you.

    -->
</head>

<body>
<div class="tm-page-container mx-auto">
    <header class="tm-header text-center">
        <h1 class="tm-title text-uppercase">Weather informer</h1>
    </header>
    <div class="tm-section">
        <form id="mainForm" method="post" action="${pageContext.request.contextPath}">
            <fieldset>
                <div>
                    <label>
                        <input type="radio" class="custom-radio" name="weatherProvider" value="WORLDWEATHERONLINE"
                               checked/>
                        World weather online
                    </label>
                    <label>
                        <input type="radio" class="custom-radio" name="weatherProvider" value="OPENWEATHERMAP"/>
                        OpenWeatherMap
                    </label>
                </div>
                <div class="tm-content-container">
                    <div class="tm-content tm-content-2">
                        <p style="display: <c:out value="${weather.temperature == null
                                                        || weather.humidity == null
                                                        || weather.description == null ? 'none' : 'block'}"/>">
                            <c:out value="According to ${weather.weatherProvider},"/>
                            <br/><c:out value="in ${weather.city} ${weather.temperature} °C,
                            humidity ${weather.humidity}%, ${weather.description}"/>
                        </p>
                        <div class="container-fluid">
                            <div class="row tm-gallery" id="tmGallery">
                                <div class="col-sm-6 tm-gallery-item">
                                    <figure class="effect-bubba">
                                        <img src="../../resources/img/gallery/Moscow.jpg" alt="Gallery item"
                                             class="img-fluid">
                                        <button style="background: transparent; border: none; font-size: 0"
                                                type="submit"
                                                onclick="document.getElementById('mainForm').submit()"
                                                name="city"
                                                value="Moscow">
                                            <figcaption>
                                                <h2>Moscow</h2>
                                            </figcaption>
                                        </button>
                                    </figure>
                                </div>
                                <div class="col-sm-6 tm-gallery-item">
                                    <figure class="effect-bubba">
                                        <img src="../../resources/img/gallery/London.jpg" alt="Gallery item"
                                             class="img-fluid"/>
                                        <button style="background: transparent; border: none; font-size: 0"
                                                type="submit" onclick="document.getElementById('mainForm').submit()"
                                                name="city"
                                                value="London">
                                            <figcaption>
                                                <h2>London</h2>
                                            </figcaption>
                                        </button>
                                    </figure>
                                </div>
                                <div class="col-sm-6 tm-gallery-item">
                                    <figure class="effect-bubba">
                                        <img src="../../resources/img/gallery/Chelyabinsk.jpg" alt="Gallery item"
                                             class="img-fluid"/>
                                        <button style="background: transparent; border: none; font-size: 0"
                                                type="submit" onclick="document.getElementById('mainForm').submit()"
                                                name="city"
                                                value="Chelyabinsk">
                                            <figcaption>
                                                <h2>Chelyabinsk</h2>
                                            </figcaption>
                                        </button>
                                    </figure>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>

    <footer>
        <span>Developed by Kirill Ponkratov</span>
        <span>e-mail: keponkratov@gmail.com<br/>tel: +7-912-777-22-35</span>
    </footer>
</div>

<script src="../../resources/js/jquery-3.4.1.min.js"></script>
<script src="../../resources/js/jquery.magnific-popup.min.js"></script>
<script>
    $(document).ready(function () {
        // Magnific Pop up
        // https://dimsemenov.com/plugins/magnific-popup/
        $('.tm-gallery').magnificPopup({
            delegate: 'a', // child items selector, by clicking on it popup will open
            type: 'image',
            gallery: {
                enabled: true
            }
        });
    });
</script>

</body>
</html>
