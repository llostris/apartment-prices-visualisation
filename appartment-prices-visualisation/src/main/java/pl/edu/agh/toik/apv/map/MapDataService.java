package pl.edu.agh.toik.apv.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.edu.agh.toik.apv.dto.DistrictData;
import pl.edu.agh.toik.apv.geojson.FeatureAssembler;
import pl.edu.agh.toik.apv.geojson.dto.Feature;
import pl.edu.agh.toik.apv.geojson.dto.FeatureCollection;
import pl.edu.agh.toik.apv.geojson.filters.iface.SimpleFilter;
import pl.edu.agh.toik.visualisation.database.dto.Offer;
import pl.edu.agh.toik.visualisation.database.service.OfferService;

import java.util.*;

/**
 * Created by Magda on 2015-06-13.
 */
@Component
public class MapDataService {

    @Autowired
    OfferService offerService;

    List<Feature> offersFeatures;

    public FeatureCollection getFilteredOffers(List<SimpleFilter> filters){
        if(null == offersFeatures){
            convertAllOffers();
        }

        List<Feature> filteredFeatures = filterFeatures(filters);

        addNormalizedWeights(filteredFeatures);

        return new FeatureCollection(filteredFeatures);
    }

    private List<Feature> filterFeatures(List<SimpleFilter> filters){
        List<Feature> features = new LinkedList<>();
        features.addAll(offersFeatures);
        for(SimpleFilter filter : filters){
            features = filter.filterFeatures(features);
        }
        return features;
    }

    private void convertAllOffers(){
        List<Offer> offers = offerService.listAllOffers();

        List<Feature> features = new ArrayList<Feature>();
        for ( Offer offer : offers ) {
            offer.calculateMeterPrice();
            Feature feature = FeatureAssembler.convert(offer);
            features.add(feature);
        }

        offersFeatures = features;
    }

	public FeatureCollection getOfferFeatures() {
        if(null == offersFeatures){
            convertAllOffers();
        }


        List<Feature> features = new ArrayList<>();

        features.addAll(offersFeatures);
		addNormalizedWeights(features);

		return new FeatureCollection(features);
	}

	public FeatureCollection getMeanPricePerDistrictFeatures() {
		List<Offer> offers = offerService.listAllOffers();

		Map<String, DistrictData> districtMap = gatherDistrictData(offers);

		List<Feature> features = new ArrayList<Feature>();

		for ( Map.Entry<String, DistrictData> entry : districtMap.entrySet() ) {
			DistrictData districtData = entry.getValue();
			districtData.calculateMeans();
			features.add(FeatureAssembler.convert(districtData));
		}

		return new FeatureCollection(features);
	}

	private void addNormalizedWeights(List<Feature> features) {
		double minPrice = Double.MAX_VALUE;
		double maxPrice = Double.MIN_VALUE;

		for ( Feature feature : features ) {
			double price = (Double) feature.getProperties().get("meterPrice");
			maxPrice = price > maxPrice ? price : maxPrice;
			minPrice = price < minPrice ? price : minPrice;
		}

		for ( Feature feature : features ) {
			double price = (Double) feature.getProperties().get("meterPrice");
			double normalized = (price - minPrice) / (maxPrice - minPrice);
			feature.getProperties().put("weight", normalized);
		}
	}

	private Map<String, DistrictData> gatherDistrictData(List<Offer> offers) {
		Map<String, DistrictData> districtMap = new HashMap<String, DistrictData>();

		for ( Offer offer : offers ) {
			String district = StringUtils.trimTrailingWhitespace(offer.getDistrict());
			if ( !districtMap.containsKey(district) ) {
				DistrictData districtData = new DistrictData(district);
				districtMap.put(district, districtData);
			}
			districtMap.get(district).addOffer(offer);
		}

		return districtMap;
	}
}
