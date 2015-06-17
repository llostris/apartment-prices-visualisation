/**
 * Created by Magda on 2015-06-16.
 */

define(['jquery'], function($) {

	var config = {
		url: '/apv-war/heatpoints/filtered',
		formId: '',
		featureCollection: [],
		configured: false,
		callback: null
	};

	var readFilterForm = function() {
		var formInputs = $("#" + config.formId).find(":input");
		var urlParameters = "";
		formInputs.each(function() {
			var parameterValue = null;
			if ( $(this).is("select") ) {
				parameterValue = $(this).children().filter(":selected").text();
			} else {
				parameterValue = $(this).val();
			}
			if ( parameterValue != null && parameterValue != '' ) {
				var parameterName = $(this).data("param");
				urlParameters += parameterName + "=" + parameterValue + "&";
			}
		});

		urlParameters = urlParameters.substring(0, urlParameters.length - 1);
		console.log("Received filter parameters: " + urlParameters);

		return urlParameters;
	};

	var getData = function(parameters) {
		console.log("Obtaining filtered data");
		$.ajax({
			url: config.url + "?" + parameters,
			success: function(data) {
				config.featureCollection = data;
				config.callback(data);
			}
		});
	};

	return {
		config: config,
		configure: function (formId, callback,  url) {
			config.formId = formId;
			config.callback = callback;
			config.configured = true;
			if ( url ) {
				config.url = url;
			}
			console.log("Filters configured");
		},
		applyFilters: function () {
			if ( config.configured || config.formId === '' ) {
				var parameters = readFilterForm();
				getData(parameters);
			} else {
				console.log("Error occurred. Filters have not been configured!");
			}
		}

	};
});