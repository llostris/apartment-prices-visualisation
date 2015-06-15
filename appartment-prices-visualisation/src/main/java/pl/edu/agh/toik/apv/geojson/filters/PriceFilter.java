package pl.edu.agh.toik.apv.geojson.filters;

import pl.edu.agh.toik.apv.geojson.filters.iface.InBetweenFilter;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public class PriceFilter extends InBetweenFilter {

    public PriceFilter(double min, double max){
        super(min,max,"price");
    }

}
