package pl.edu.agh.toik.apv.geojson.dto;

import pl.edu.agh.toik.apv.geojson.GeoJsonConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magda on 2015-06-13.
 */
public class FeatureCollection {

	private String type;

	private List<Feature> features;

	private CoordinateReferenceSystem crs;

	public FeatureCollection() {
		features = new ArrayList<Feature>();
		crs = new CoordinateReferenceSystem();
	}

	public FeatureCollection(List<Feature> features) {
		this.type = GeoJsonConstants.FEATURE_COLLECTION_TYPE;
		this.features = features;
		this.crs = new CoordinateReferenceSystem();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public CoordinateReferenceSystem getCrs() {
		return crs;
	}

	public void setCrs(CoordinateReferenceSystem crs) {
		this.crs = crs;
	}
}
