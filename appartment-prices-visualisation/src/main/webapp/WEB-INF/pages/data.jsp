<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Appartment Prices Visualisation</title>
    <script data-main="${pageContext.request.contextPath}/resources/js/datalist.js" src="${pageContext.request.contextPath}/resources/js/require.js"></script>

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
                <li><a href="./">Home</a></li>
                <li class="active"><a href="./data">Data</a></li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <div class="main-content">
        <div class="row">
            <h2>Filter</h2>
            <%@include file="filter.jsp"%>
        </div>
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Street</th>
                        <th>District</th>
                        <th>Price</th>
                        <th>Area</th>
                        <th>Price per m<sup>2</sup></th>
                        <th>Rooms</th>
                        <th>Type</th>
                    </tr>
                </thead>
                <tbody id="offerData">
                    <c:forEach items="${offers}" var="offer">
                        <tr>
                            <td>${offer.id}</td>
                            <td>${offer.street}</td>
                            <td>${offer.district}</td>
                            <td>${offer.price}</td>
                            <td>${offer.area}</td>
                            <td>${offer.meterPrice}</td>
                            <td>${offer.rooms}</td>
                            <td>${offer.type}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>