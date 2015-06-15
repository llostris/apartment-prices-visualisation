package pl.edu.agh.toik.apv.geojson.dto;

import pl.edu.agh.toik.apv.geojson.FilterType;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Puszek_SE on 2015-06-15.
 */
public class Filter<T extends Comparable> {

    private final String filterType;
    private T value;
    private String key;

    public Filter(String filterType,String key,T value){
        this.filterType = filterType;
        this.value = value;
        this.key = key;
    }

    public List<Feature> filterOut(List<Feature> features){
        Iterator<Feature> iterator = features.iterator();
        while (iterator.hasNext()){
        Feature feature = iterator.next();
            switch(filterType){
                case FilterType.MAX:
                    if(0>value.compareTo(feature.getProperties().get(key))){
                        features.remove(feature);
                    }
                    break;
                case FilterType.MIN:
                    if(0<value.compareTo(feature.getProperties().get(key))){
                        features.remove(feature);
                    }
                    break;
                case FilterType.EQUAL:
                    if(false==value.equals(feature.getProperties().get(key))){
                        features.remove(feature);
                    }
            }
        }
        return features;
    }

    public boolean check(Feature feature){
        switch(filterType){
            case FilterType.MAX:
                if(0>value.compareTo(feature.getProperties().get(key))){
                    return false;
                }
                break;
            case FilterType.MIN:
                if(0<value.compareTo(feature.getProperties().get(key))){
                    return false;
                }
                break;
            case FilterType.EQUAL:
                if(false==value.equals(feature.getProperties().get(key))){
                    return false;
                }
        }
        return true;
    }

}
