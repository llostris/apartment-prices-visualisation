/**
 * Created by Magda on 2015-06-08.
 */

require.config({
	paths: {
		jquery: 'jquery-1.11.3.min',
		OpenLayers: 'ol',
		bootstrap: 'bootstrap.min',
		filters: 'filters'
	},
	shim: {
		bootstrap: [ "jquery" ]
	}
});


require([ "jquery", "OpenLayers", "filters", "bootstrap" ], function($, ol, filters) {
	console.log("Loading map.js");

	// helper object
	var mapState = {
		heat: true,
		markers: false,
		districts: false,
		previousState: null
	};

	var heatLayer, markerLayer, districtLayer;
	var offerLayers = [];

	var getFeaturesInMapProjection = function(data) {
		return (new ol.format.GeoJSON()).readFeatures(data, {
			dataProjection: "EPSG:4326",
			featureProjection: "EPSG:3857"
		})
	};

	// labels and styles

	var iconStyle = new ol.style.Style({
		image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
			anchor: [0.5, 46],
			anchorXUnits: 'fraction',
			anchorYUnits: 'pixels',
			opacity: 1.0,
			src: '/apv-war/resources/img/map-marker.png'
		}))
	});

	var getDistrictLabel = function(feature) {
		var properties = feature.getProperties();
		return properties["district"] + ": " + properties["price"].toFixed(2);
	};

	var textStyleFunction = function(feature, resolution) {
		return new ol.style.Text({
			textAlign: 'center',
			textBaseline: 'middle',
			font: 'bold 12px Arial',
			fill: new ol.style.Fill({color: '#000000'}),
			stroke: new ol.style.Stroke({color: '#ffffff', width: 2}),
			text: getDistrictLabel(feature)
		});
	};

	var districtStyleFunction = function(feature, resolution) {
		var style = new ol.style.Style({
			text: textStyleFunction(feature, resolution)
		});
		return [style];
	};

	// map initialization function

	var initializeMap = function(heatPoints) {
		heatLayer = new ol.layer.Heatmap({
			source: new ol.source.Vector({
				features: getFeaturesInMapProjection(heatPoints)
			}),
			blur: 10,
			radius: 30
		});

		markerLayer = new ol.layer.Vector({
			source: new ol.source.Vector({
				features: getFeaturesInMapProjection(heatPoints)
			}),
			style: iconStyle,
			visible: false
		});

		districtLayer = new ol.layer.Vector({
			source: new ol.source.Vector({
				url: "/apv-war/districts",
				format: new ol.format.GeoJSON()
			}),
			visible: false,
			style: districtStyleFunction
		});

		var heatMap = new ol.Map({
			target: 'heatMap',
			layers: [
				new ol.layer.Tile({
					source: new ol.source.OSM()
				}),
				heatLayer,
				markerLayer,
				districtLayer
			],
			view: new ol.View({
				center: ol.proj.transform([19.9451349, 50.052938], 'EPSG:4326', 'EPSG:3857'),
				zoom: 12
			})
		});

		// select-a-feature feature

		var featureNames = [ 'area', 'price', 'rooms', 'street', 'district', 'type', 'meterPrice' ];

		heatMap.on('singleclick', function(event) {
			heatMap.forEachFeatureAtPixel(event.pixel, function (feature, layer) {
				if ( layer === markerLayer ) {
					var properties = feature.getProperties();
					for ( var index = 0; index < featureNames.length; index++ ) {
						var featureName = featureNames[index];
						$("#pointDetails_" + featureName).text(properties[featureName]);
					}
				}
			});
		});

		offerLayers = [ heatLayer, markerLayer ];
	};

	// load unfiltered data

	$.ajax({
		url: "/apv-war/heatpoints/",
		success: function(data) {
			initializeMap(data);
		}
	});

	// map navigation bar

	$("#toggleMarkersButton").on('click', function() {
		if ( mapState.markers ) {
			markerLayer.setVisible(false);
			$("span[id^='pointDetails_']").text("");
		} else {
			markerLayer.setVisible(true);
		}
		mapState.markers = !mapState.markers;
	});

	$("#toggleHeatMapButton").on('click', function() {
		if ( mapState.heat ) {
			heatLayer.setVisible(false);
		} else {
			heatLayer.setVisible(true);
		}
		mapState.heat = !mapState.heat;
	});

	$("#toggleDistrictDataButton").on('click', function() {
		if ( mapState.districts ) {
			districtLayer.setVisible(false);
		} else {
			districtLayer.setVisible(true);
		}
		mapState.districts = !mapState.districts;
	});

	// filters

	var setFilteredFeatures = function(data) {
		for ( var i = 0; i < offerLayers.length; i++ ) {
			offerLayers[i].getSource().clear();
			var features = getFeaturesInMapProjection(data);
			offerLayers[i].getSource().addFeatures(features);
		}
	};

	filters.configure("filterForm", setFilteredFeatures);
	$("#filterButton").on('click', filters.applyFilters);

	console.log("map.js loaded");
});