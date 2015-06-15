package pl.edu.agh.toik.apv.geojson.filters.iface;

import pl.edu.agh.toik.apv.geojson.dto.Feature;

import java.util.List;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public interface SimpleFilter {

    public boolean checkProperty(Feature feature);

    public List<Feature> filterFeatures(List<Feature> features);

}
