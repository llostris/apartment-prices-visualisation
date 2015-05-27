package pl.edu.agh.toik.apv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.toik.visualisation.database.dao.OfferDAO;
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

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
        List<Offer> offers = new LinkedList<Offer>(offerService.listAllOffers());

        model.addAttribute("offer",offers.get(0).toString());

		return "hello";
	}
}
