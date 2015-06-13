package pl.edu.agh.toik.apv.geojson.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Magda on 2015-06-13.
 */
public class Feature implements Serializable {

	private String type;

	private Geometry geometry;

	private Map<String, Object> properties;

	public Feature() {
		type = "Feature";
		properties = new HashMap<String, Object>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
}
