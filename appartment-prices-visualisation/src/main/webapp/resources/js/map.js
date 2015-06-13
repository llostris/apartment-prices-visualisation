/**
 * Created by Magda on 2015-06-08.
 */

require.config({
	paths: {
		jquery: 'jquery-1.11.3.min',
		OpenLayers: 'ol'
	}
});

require([ "jquery", "OpenLayers" ], function($, ol) {
	console.log("Loading map.js");

	var heat = new ol.layer.Heatmap({
		source: new ol.source.Vector({
			url: "/apv-war/heatpoints/", // TODO: don't hardcode the path ?
			format: new ol.format.GeoJSON()
		}),
		blur: 5,
		radius: 15
	});

	var heatMap = new ol.Map({
		target: 'heatMap',
		layers: [
			new ol.layer.Tile({
				source: new ol.source.OSM()
			}),
			heat
		],
		view: new ol.View({
			center: ol.proj.transform([19.9451349, 50.052938], 'EPSG:4326', 'EPSG:3857'),
			zoom: 12
		})
	});

	console.log("map.js loaded");
});