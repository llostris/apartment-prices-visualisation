package pl.edu.agh.toik.visualisation.database.dto;

import pl.edu.agh.toik.visualisation.database.dto.enums.OfferType;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Magda on 2015-05-06.
 */

//@Entity(name = "\"OFFER\"")
@Entity(name = "offer")
public class Offer implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    //@Column(name="\"ID\"")
    @Column(name="id")
	private long id;

    //@Column(name="\"OFFER_ID\"")
    @Column(name="offer_id")
	private long offerId;

    //@Column(name="\"TYPE\"")
    @Column(name="type")
	private String type;

    @Transient
	private OfferType offerType;

    //@Column(name="\"CITY\"")
    @Column(name="city")
	private String city;

    //@Transient
    @Column(name="rooms")
	private int rooms;

    //@Column(name="\"PRICE\"")
    @Column(name="price")
	private double price;

    //@Column(name="\"AREA\"")
    @Column(name="area")
	private double area;

    //@Column(name="\"LATITUDE\"")
    @Column(name="latitude")
	private double latitude;

    //@Column(name="\"LONGITUDE\"")
    @Column(name="longitude")
	private double longitude;

    @Column(name="district")
    private String district;

    @Column(name="street")
    private String street;

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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
	public String toString() {
		return type + "," +
				city + "," +
				offerType + "," +
				price + "," +
				area + "," +
				latitude + "," +
				longitude + "," +
				street + "," +
				district;
	}
}
