package pl.edu.agh.toik.apv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.toik.visualisation.database.dao.OfferDAO;

/**
 * Created by Magda on 2015-05-27.
 */
@Controller
@RequestMapping("/")
public class VisualisationController {

//	@Autowired
//	OfferDAO offerDAO;

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}
}
