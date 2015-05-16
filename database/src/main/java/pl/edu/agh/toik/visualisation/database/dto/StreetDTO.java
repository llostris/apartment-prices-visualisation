package pl.edu.agh.toik.visualisation.database.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Puszek_SE on 2015-05-13.
 */

@Entity(name="STREET")
public class StreetDTO implements Serializable{

    @Id
    @Column(name="ID")
    int ID;

    @Column(name="CITY")
    String city;

    //@Id?
    @Column(name="NAME")
    String name;

    @Column(name="Cooridnates")
    String coordinateList;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(String coordinateList) {
        this.coordinateList = coordinateList;
    }
}
