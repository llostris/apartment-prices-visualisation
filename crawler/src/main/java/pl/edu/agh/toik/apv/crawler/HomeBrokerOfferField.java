package pl.edu.agh.toik.apv.crawler;

import pl.edu.agh.toik.visualisation.database.dto.Offer;

/**
 * Created by Magda on 2015-05-06.
 */
public enum HomeBrokerOfferField {
	CITY_ID("miasto"),
	TYPE_ID("TypLokalu"),
	OFFER_TYPE_ID(""),
	ROOMS_ID("LiczbaPokoi"),
	AREA_ID("Metry"),
	PRICE_ID("Price"),
	OFFER_ID("idoferty");
    /*STREET(""),
    DISTRICT("");*/
	private final String id;

	HomeBrokerOfferField(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public static HomeBrokerOfferField getInstanceForId(String id) {
		for ( HomeBrokerOfferField homeBrokerOfferField : HomeBrokerOfferField.values() ) {
			if ( homeBrokerOfferField.getId().equals(id) ) {
				return homeBrokerOfferField;
			}
		}
		return null;
	}

	public void fillOfferField(Offer offer, String value) {
		try {
			switch (this) {
				case CITY_ID:
					offer.setCity(value);
					break;
				case TYPE_ID:
					offer.setType(value);
					break;
//			case OFFER_TYPE_ID:
//				offer.setOfferType(value);
//				break;
				case ROOMS_ID:
					offer.setRooms(Integer.parseInt(value));
					break;
				case AREA_ID:
					offer.setArea(Double.parseDouble(value));
					break;
				case PRICE_ID:
					offer.setPrice(Double.parseDouble(value));
					break;
				case OFFER_ID:
					offer.setOfferId(Long.parseLong(value));
					break;
			}
		} catch ( NumberFormatException e ) {
			e.printStackTrace();
		}

	}
}
