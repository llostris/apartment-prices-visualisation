package pl.edu.agh.toik.apv.geojson.filters.iface;

import pl.edu.agh.toik.apv.geojson.dto.Feature;

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

    public void setMax(double max) {
        this.max = max;
    }

    public void setMinMax(double min, double max){
        this.min = min;
        this.max = max;
    }

	public void setMinMaxOrClear(Double min, Double max) {
		if ( min != null && max != null ) {
			setMinMax(min, max);
		} else {
			setMinMax(0.0, 0.0);
		}
	}
}
