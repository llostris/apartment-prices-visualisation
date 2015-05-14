package pl.edu.agh.toik.apv.crawler;

import pl.edu.agh.toik.apv.enums.OfferType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Magda on 2015-05-13.
 */
public class HomeBrokerOfferConstants {

	public static Map<String, OfferType> OFFER_TYPES_MAP = new HashMap<String, OfferType>() {{
		put("wynajem", OfferType.RENT);
		put("sprzedaz", OfferType.SELL);
	}};
}
