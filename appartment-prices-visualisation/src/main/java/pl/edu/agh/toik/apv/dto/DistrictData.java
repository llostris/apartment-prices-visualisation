package pl.edu.agh.toik.apv.dto;

import pl.edu.agh.toik.visualisation.database.dto.Offer;

/**
 * Created by Magda on 2015-06-14.
 */
public class DistrictData {

	private String district;

	private double latitudeSum;

	private double longitudeSum;

	private double priceSum;

	private int numberOfOffers = 0;

	private double meanLatitude;

	private double meanLongitude;

	private double meanPrice;

	public DistrictData() {}

	public DistrictData(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public double getMeanLatitude() {
		return meanLatitude;
	}

	public double getMeanLongitude() {
		return meanLongitude;
	}

	public double getMeanPrice() {
		return meanPrice;
	}

	public void addOffer(Offer offer) {
		latitudeSum += offer.getLatitude();
		longitudeSum += offer.getLongitude();
		priceSum += offer.getPrice();
		numberOfOffers += 1;
	}

	public void calculateMeans() {
		meanPrice = priceSum / numberOfOffers;
		meanLatitude = latitudeSum / numberOfOffers;
		meanLongitude = longitudeSum / numberOfOffers;
	}
}
