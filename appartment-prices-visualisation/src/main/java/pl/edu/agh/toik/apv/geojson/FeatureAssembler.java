package pl.edu.agh.toik.apv.geojson;

import pl.edu.agh.toik.apv.dto.DistrictData;
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
public class FeatureAssembler {

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

	public static Feature convert(DistrictData districtData) {
		if ( districtData == null ) {
			return null;
		}

		List<Double> coordinates = Arrays.asList(districtData.getMeanLongitude(), districtData.getMeanLatitude());
		Geometry geometry = new Geometry();
		geometry.setType(GeoJsonConstants.POINT_TYPE);
		geometry.setCoordinates(coordinates);

		Feature feature = new Feature();
		feature.setType(GeoJsonConstants.FEATURE_TYPE);
		feature.setGeometry(geometry);
		feature.getProperties().put("price", districtData.getMeanPrice());
		feature.getProperties().put("district", districtData.getDistrict());

		return feature;
	}

	private static Map<String, Object> getPropertiesMap(Offer offer) {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("price", offer.getPrice());
		properties.put("area", offer.getArea());
		properties.put("rooms", offer.getRooms());
		properties.put("district", offer.getDistrict());
		properties.put("street", offer.getStreet());
		properties.put("type", offer.getType());
		properties.put("meterPrice", offer.getMeterPrice());
		properties.put("id", offer.getId());
		return properties;
	}
}
