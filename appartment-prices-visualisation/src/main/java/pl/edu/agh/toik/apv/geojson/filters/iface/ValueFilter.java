package pl.edu.agh.toik.apv.geojson.filters.iface;

import pl.edu.agh.toik.apv.geojson.dto.Feature;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public abstract class ValueFilter extends AbstractFilter {

    protected String value;

    protected ValueFilter(String value,String key){
        super(key);
        this.value = value;
    }

    @Override
    public boolean checkProperty(Feature feature) {
        String value = (String) feature.getProperties().get(key);
        return this.value.equals(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
