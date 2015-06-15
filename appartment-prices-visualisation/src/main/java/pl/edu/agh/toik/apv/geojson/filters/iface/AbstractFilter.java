package pl.edu.agh.toik.apv.geojson.filters.iface;

import pl.edu.agh.toik.apv.geojson.dto.Feature;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public abstract class AbstractFilter implements SimpleFilter {

    protected final String key;

    protected AbstractFilter(String key){
        this.key = key;
    }

    @Override
    public List<Feature> filterFeatures(List<Feature> features) {
        Iterator<Feature> iterator = features.iterator();
        while (iterator.hasNext()) {
            Feature feature = iterator.next();
            if(false == checkProperty(feature)){
                features.remove(feature);
            }
        }
        return features;
    }

}
