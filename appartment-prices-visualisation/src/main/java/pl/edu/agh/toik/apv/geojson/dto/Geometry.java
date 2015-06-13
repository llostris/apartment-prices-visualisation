package pl.edu.agh.toik.apv.geojson.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magda on 2015-06-13.
 */
public class Geometry implements Serializable {

	private String type;

	private List<Double> coordinates;

	public Geometry() {
		coordinates = new ArrayList<Double>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
}
