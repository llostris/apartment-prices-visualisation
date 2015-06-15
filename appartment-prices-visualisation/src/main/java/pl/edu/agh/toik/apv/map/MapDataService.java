package pl.edu.agh.toik.apv.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.edu.agh.toik.apv.dto.DistrictData;
import pl.edu.agh.toik.apv.geojson.FeatureAssembler;
import pl.edu.agh.toik.apv.geojson.dto.Feature;
import pl.edu.agh.toik.apv.geojson.dto.FeatureCollection;
import pl.edu.agh.toik.visualisation.database.dto.Offer;
import pl.edu.agh.toik.visualisation.database.service.OfferService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Magda on 2015-06-13.
 */
@Component
public class MapDataService {

	@Autowired
	OfferService offerService;

	public FeatureCollection getOfferFeatures() {
		List<Offer> offers = offerService.listAllOffers();

		List<Feature> features = new ArrayList<Feature>();
		for ( Offer offer : offers ) {
			offer.calculateMeterPrice();
			Feature feature = FeatureAssembler.convert(offer);
			features.add(feature);
		}

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
