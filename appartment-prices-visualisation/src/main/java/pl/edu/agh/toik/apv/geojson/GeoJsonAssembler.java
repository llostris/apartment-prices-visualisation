package pl.edu.agh.toik.apv.geojson;

import pl.edu.agh.toik.apv.geojson.dto.Feature;
import pl.edu.agh.toik.apv.geojson.dto.Geometry;
import pl.edu.agh.toik.visualisation.database.dto.Offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Magda on 2015-06-13.
 */
public class GeoJsonAssembler {

	public static Feature convert(Offer offer) {
		if ( offer == null ) {
			return null;
		}
		List<Double> coordinates = Arrays.asList(offer.getLongitude(), offer.getLatitude());

		Geometry geometry = new Geometry();
		geometry.setType(GeoJsonConstants.POINT_TYPE);
		geometry.setCoordinates(coordinates);

		Feature feature = new Feature();
		feature.setType(GeoJsonConstants.FEATURE_TYPE);
		feature.setGeometry(geometry);
		feature.setProperties(getPropertiesMap(offer));

		return feature;
	}

	private static Map<String, Object> getPropertiesMap(Offer offer) {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("price", offer.getPrice());
		properties.put("area", offer.getArea());
		return properties;
	}
}
