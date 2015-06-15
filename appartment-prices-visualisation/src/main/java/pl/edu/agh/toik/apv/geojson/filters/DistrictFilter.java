package pl.edu.agh.toik.apv.geojson.filters;

import pl.edu.agh.toik.apv.geojson.filters.iface.ValueFilter;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public class DistrictFilter extends ValueFilter {

    public DistrictFilter(String district){
        super(district,"district");
    }
}
