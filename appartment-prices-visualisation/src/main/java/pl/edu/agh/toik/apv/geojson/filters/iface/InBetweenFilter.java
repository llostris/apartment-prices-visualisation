package pl.edu.agh.toik.apv.geojson.filters.iface;

import pl.edu.agh.toik.apv.geojson.dto.Feature;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public abstract class InBetweenFilter extends AbstractFilter{
    protected Double min,max;

    protected boolean inBetween(Double area){
        return (null==min || area>=min) && (null==max || area<=max);
    }

    protected InBetweenFilter(Double min, Double max,String key){
        super(key);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean checkProperty(Feature feature) {
        Double area = (Double) feature.getProperties().get(key);
        return inBetween(area);
    }

    public void setMinMax(Double min, Double max){
        this.min = min;
        this.max = max;
    }

}
