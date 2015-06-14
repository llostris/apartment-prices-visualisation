package pl.edu.agh.toik.apv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.agh.toik.apv.geojson.dto.FeatureCollection;
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

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
	public String printWelcome(ModelMap model) {
        List<Offer> offers = new LinkedList<Offer>(offerService.listAllOffers());

        model.addAttribute("offer",offers.get(0).toString());

		return "hello";
	}

	@RequestMapping(value = "/heatpoints", method = RequestMethod.GET)
	public @ResponseBody FeatureCollection getHeatPoints() {
		return mapDataService.getOfferFeatures();
	}

	@RequestMapping(value = "/districts", method = RequestMethod.GET)
	public @ResponseBody FeatureCollection getDistrictPrices() {
		return mapDataService.getMeanPricePerDistrictFeatures();
	}
}
