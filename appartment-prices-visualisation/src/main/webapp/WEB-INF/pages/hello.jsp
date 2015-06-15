<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Appartment Prices Visualisation</title>
    <script data-main="${pageContext.request.contextPath}/resources/js/map.js" src="${pageContext.request.contextPath}/resources/js/require.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ol.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Appartment Prices Visualisation</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="./">Home</a></li>
                    <li><a href="./data">Data</a></li>
                    <%--<li><a href="#contact">Contact</a></li>--%>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </nav>

    <div class="container">
        <div class="main-content">

            <div class="page-header">
                 <h1>Map</h1>
            </div>

            <div>
                <div class="row">
                    <div class="col-md-9">
                        <button type="button" class="btn btn-primary" id="toggleHeatMapButton">Show/hide heat</button>
                        <button type="button" class="btn btn-primary" id="toggleMarkersButton">Show/hide markers</button>
                        <button type="button" class="btn btn-primary" id="toggleDistrictDataButton">Show/hide districts</button>
                        <!-- TODO: add filters -->
                    </div>
                    <div class="col-md-3">
                        <!-- TODO: vertical alignment? -->
                        <h3>Point Details</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <div id="heatMap"></div>
                    </div>
                    <div class="col-md-3">
                        <div id="pointDetails" class="text-left">
                            <p>
                                <b>Area: </b><span id="pointDetails_area"></span>
                            </p>
                            <p>
                                <b>Rooms: </b><span id="pointDetails_rooms"></span>
                            </p>
                            <p>
                                <b>Price: </b><span id="pointDetails_price"></span>
                            </p>
                            <p>
                                <b>Price per m<sup>2</sup>: </b><span id="pointDetails_meterPrice"></span>
                            </p>
                            <p>
                                <b>Street: </b><span id="pointDetails_street"></span>
                            </p>
                            <p>
                                <b>District: </b><span id="pointDetails_district"></span>
                            </p>
                            <p>
                                <b>Type: </b><span id="pointDetails_type"></span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>