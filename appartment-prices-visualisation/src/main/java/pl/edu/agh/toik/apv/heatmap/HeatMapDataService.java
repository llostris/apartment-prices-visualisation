package pl.edu.agh.toik.apv.heatmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.toik.apv.geojson.GeoJsonAssembler;
import pl.edu.agh.toik.apv.geojson.dto.Feature;
import pl.edu.agh.toik.apv.geojson.dto.FeatureCollection;
import pl.edu.agh.toik.visualisation.database.dto.Offer;
import pl.edu.agh.toik.visualisation.database.service.OfferService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magda on 2015-06-13.
 */
@Component
public class HeatMapDataService {

	@Autowired
	OfferService offerService;

	public FeatureCollection buildGeoJsonStructure() {
		List<Offer> offers = offerService.listAllOffers();

		List<Feature> features = new ArrayList<Feature>();
		for ( Offer offer : offers ) {
			Feature feature = GeoJsonAssembler.convert(offer);
			features.add(feature);
		}

		addNormalizedWeights(features);

		return new FeatureCollection(features);
	}

	private void addNormalizedWeights(List<Feature> features) {
		double minPrice = Double.MAX_VALUE;
		double maxPrice = Double.MIN_VALUE;

		for ( Feature feature : features ) {
			double price = (Double) feature.getProperties().get("price");
			minPrice = price < minPrice ? price : minPrice;
			maxPrice = price > maxPrice ? price : maxPrice;
		}

		for ( Feature feature : features ) {
			double price = (Double) feature.getProperties().get("price");
			double normalized = (price - minPrice) / (maxPrice - minPrice);
			feature.getProperties().put("weight", normalized);
		}
	}
}
