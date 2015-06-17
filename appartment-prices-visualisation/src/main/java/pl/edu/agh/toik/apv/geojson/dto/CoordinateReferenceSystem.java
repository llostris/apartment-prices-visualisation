package pl.edu.agh.toik.apv.geojson.dto;

import pl.edu.agh.toik.apv.geojson.GeoJsonConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Magda on 2015-06-17.
 */
public class CoordinateReferenceSystem {

	private String type;

	private Map<String, String> properties;

	CoordinateReferenceSystem() {
		type = GeoJsonConstants.CRS_TYPE;
		properties = new HashMap<String, String>();
		properties.put(GeoJsonConstants.CRS_TYPE, "EPSG:4326");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
