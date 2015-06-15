package pl.edu.agh.toik.apv.geojson.filters;

import pl.edu.agh.toik.apv.geojson.filters.iface.ValueFilter;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public class TypeFilter extends ValueFilter{

    public TypeFilter(String type){
        super(type,"type");
    }

}
