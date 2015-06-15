package pl.edu.agh.toik.apv.geojson.dto;

import pl.edu.agh.toik.apv.geojson.GeoJsonConstants;
import pl.edu.agh.toik.apv.geojson.filters.complex.Filter;
import pl.edu.agh.toik.apv.geojson.filters.iface.SimpleFilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Magda on 2015-06-13.
 */
public class FeatureCollection {

	private String type;

	private List<Feature> features;
    private List<SimpleFilter> filters;

	public FeatureCollection() {
		features = new ArrayList<Feature>();
	}

	public FeatureCollection(List<Feature> features) {
		this.type = GeoJsonConstants.FEATURE_COLLECTION_TYPE;
		this.features = features;
	}

    public FeatureCollection(List<Feature> features, List<SimpleFilter> filters){
        Iterator<Feature> iterator = features.iterator();
        while (iterator.hasNext()){
            Feature feature = iterator.next();
            for(SimpleFilter filter : filters){
                if(false == filter.checkProperty(feature)){
                    features.remove(feature);
                    break;
                }
            }
        }
        this.type = GeoJsonConstants.FEATURE_COLLECTION_TYPE;
        this.features = features;
        this.filters = filters;
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
}
