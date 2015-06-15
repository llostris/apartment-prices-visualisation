package pl.edu.agh.toik.apv.geojson.filters;

import pl.edu.agh.toik.apv.geojson.filters.iface.InBetweenFilter;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public class AreaFilter extends InBetweenFilter {

    public AreaFilter(double min, double max){
        super(min,max,"key");
    }

}