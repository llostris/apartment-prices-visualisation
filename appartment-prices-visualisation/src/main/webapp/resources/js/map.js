/**
 * Created by Magda on 2015-06-08.
 */

require.config({
	paths: {
		jquery: 'jquery-1.11.3.min',
		OpenLayers: 'ol',
		bootstrap: 'bootstrap.min'
	},
	shim: {
		bootstrap: [ "jquery" ]
	}
});

require([ "jquery", "OpenLayers", "bootstrap" ], function($, ol) {
	console.log("Loading map.js");

	// helper object
	var mapState = {
		heat: true,
		markers: false,
		tooltip: false
	};

	var heatPoints = {};
	$.ajax({
		url: "/apv-war/heatpoints/",
		success: function(data) {
			heatPoints = data;
		}
	});

	// TODO: use previously downloaded heatpoints instead of downloading same data twice

	var iconStyle = new ol.style.Style({
		image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
			anchor: [0.5, 46],
			anchorXUnits: 'fraction',
			anchorYUnits: 'pixels',
			opacity: 0.75,
			src: '/apv-war/resources/img/map-marker.png'
		}))
	});

	var heatLayer = new ol.layer.Heatmap({
		source: new ol.source.Vector({
			url: "/apv-war/heatpoints/",
			format: new ol.format.GeoJSON()
		}),
		blur: 10,
		radius: 30
	});

	var markerLayer = new ol.layer.Vector({
		source: new ol.source.Vector({
			//features: heatpoints.features
			url: "/apv-war/heatpoints/",
			format: new ol.format.GeoJSON()
		}),
		style: iconStyle
	});

	var heatMap = new ol.Map({
		target: 'heatMap',
		layers: [
			new ol.layer.Tile({
				source: new ol.source.OSM()
			}),
			heatLayer
		],
		view: new ol.View({
			center: ol.proj.transform([19.9451349, 50.052938], 'EPSG:4326', 'EPSG:3857'),
			zoom: 12
		})
	});

	// select-a-feature feature

	var featureNames = [ 'area', 'price', 'rooms', 'street', 'district', 'type' ];

	heatMap.on('singleclick', function(event) {
		console.log(event);
		heatMap.forEachFeatureAtPixel(event.pixel, function (feature, layer) {
			if ( layer === markerLayer ) {
				var properties = feature.getProperties();
				for ( var index = 0; index < featureNames.length; index ++ ) {
					var featureName = featureNames[index];
					$("#pointDetails_" + featureName).text(properties[featureName]);
				}
			}
		});
	});

	// map navigation bar

	$("#toggleMarkersButton").on('click', function() {
		console.log("toggleMarkersButton clicked");
		if ( mapState.markers ) {
			heatMap.removeLayer(markerLayer);
			$("span[id^='pointDetails_']").text("");
		} else {
			heatMap.addLayer(markerLayer);
		}
		mapState.markers = !mapState.markers;
	});

	$("#toggleHeatMapButton").on('click', function() {
		if ( mapState.heat ) {
			heatMap.removeLayer(heatLayer);
		} else {
			heatMap.addLayer(heatLayer);
		}
		mapState.heat = !mapState.heat;
	});

	console.log("map.js loaded");
});