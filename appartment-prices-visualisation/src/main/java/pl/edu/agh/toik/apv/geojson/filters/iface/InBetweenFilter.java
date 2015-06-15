package pl.edu.agh.toik.apv.geojson.filters.iface;

import pl.edu.agh.toik.apv.geojson.dto.Feature;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public abstract class InBetweenFilter extends AbstractFilter{
    protected double min,max;

    protected boolean inBetween(double area){
        return area>=min&&area<=max;
    }

    protected InBetweenFilter(double min, double max,String key){
        super(key);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean checkProperty(Feature feature) {
        double area = (double) feature.getProperties().get(key);
        return inBetween(area);
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
