/**
 * Created by Magda on 2015-06-17.
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


require([ "jquery", "filters", "bootstrap", "OpenLayers" ], function($, filters) {

	// callback function

	var propertyNames = [ "id", "street", "district", "price", "area", "meterPrice", "rooms", "type" ];

	var displayFilteredData = function(data) {
		var tableBody = $("#offerData");
		tableBody.children().remove();
		for ( var i = 0; i < data.features.length ; i++ ) {
			var feature = data.features[i];
			var row = $("<tr></tr>");
			for ( var j = 0; j < propertyNames.length; j++ ) {
				var property = propertyNames[j];
				var column = $("<td></td>").append(feature.properties[property]);
				row.append(column);
			}
			tableBody.append(row);
		}
	};

	// filters

	filters.configure("filterForm", displayFilteredData);
	$("#filterButton").on('click', filters.applyFilters);

});