<html>
<head>
    <title>Appartment Prices Visualisation</title>
    <script data-main="${pageContext.request.contextPath}/resources/js/map.js" src="${pageContext.request.contextPath}/resources/js/require.js"></script>

    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ol.css"/>

</head>
<body>
	<h1>${message}</h1>
	<h2>${offer}</h2>

    <div id="heatMap"></div>
</body>
</html>