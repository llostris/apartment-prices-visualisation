package pl.edu.agh.toik.apv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.agh.toik.apv.geojson.dto.FeatureCollection;
import pl.edu.agh.toik.apv.geojson.filters.AreaFilter;
import pl.edu.agh.toik.apv.geojson.filters.DistrictFilter;
import pl.edu.agh.toik.apv.geojson.filters.PriceFilter;
import pl.edu.agh.toik.apv.geojson.filters.TypeFilter;
import pl.edu.agh.toik.apv.geojson.filters.iface.SimpleFilter;
import pl.edu.agh.toik.apv.map.MapDataService;
import pl.edu.agh.toik.visualisation.database.dto.Offer;
import pl.edu.agh.toik.visualisation.database.service.OfferService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Magda on 2015-05-27.
 */
@Controller
@RequestMapping("/")
public class VisualisationController {

	@Autowired
    OfferService offerService;

	@Autowired
	MapDataService mapDataService;

    private PriceFilter priceFilter = new PriceFilter(0,0);
    private AreaFilter areaFilter = new AreaFilter(0,0);
    private DistrictFilter districtFilter = new DistrictFilter("");
    private TypeFilter typeFilter = new TypeFilter("");

    private List<SimpleFilter> filters = new LinkedList<SimpleFilter>(){{
        add(priceFilter);
        add(areaFilter);
        add(districtFilter);
        add(typeFilter);
    }};

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
	public String printWelcome(ModelMap model) {
		model.addAttribute("districts", offerService.getAllDistricts());
		return "index";
	}

	@RequestMapping(value="/data", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String listData(ModelMap model) {
		List<Offer> offers = offerService.listAllOffers();

		model.addAttribute("offers", offers);

		return "data";
	}

	@RequestMapping(value = "/heatpoints", method = RequestMethod.GET)
	public @ResponseBody FeatureCollection getHeatPoints() {
		return mapDataService.getOfferFeatures();
	}

    @RequestMapping(value = "/heatpoints/filtered", method = RequestMethod.GET)
    public @ResponseBody FeatureCollection getHeatPoints(@RequestParam(value = "area-min", required = false) Double areaMin,
                    @RequestParam(value = "area-max", required = false) Double areaMax, @RequestParam(value = "price-min", required = false) Double priceMin,
                    @RequestParam(value = "price-max", required = false) Double priceMax, @RequestParam(value = "district", required = false) String district,                     @RequestParam(value = "type", required = false) String offerType) {

        areaFilter.setMinMax(areaMin, areaMax);
        priceFilter.setMinMax(priceMin, priceMax);
	    districtFilter.setValue(district);
	    typeFilter.setValue(offerType);

        return mapDataService.getFilteredOffers(filters);
    }
	@RequestMapping(value = "/districts", method = RequestMethod.GET)
	public @ResponseBody FeatureCollection getDistrictPrices() {
		return mapDataService.getMeanPricePerDistrictFeatures();
	}

}
