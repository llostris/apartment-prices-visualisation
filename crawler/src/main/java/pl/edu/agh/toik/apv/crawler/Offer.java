package pl.edu.agh.toik.apv.crawler;

import pl.edu.agh.toik.apv.enums.OfferType;

/**
 * Created by Magda on 2015-05-06.
 */
public class Offer {

	private long id;

	private long offerId;

	private String type;

	private OfferType offerType;

	private String city;

	private int rooms;

	private double price;

	private double area;

	private double latitude;

	private double longitude;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return type + "," +
				city + "," +
				offerType + "," +
				price + "," +
				area + "," +
				latitude + "," +
				longitude;
	}
}
