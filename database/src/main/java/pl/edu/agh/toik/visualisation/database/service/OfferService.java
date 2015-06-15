package pl.edu.agh.toik.visualisation.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.toik.visualisation.database.dao.OfferDAO;
import pl.edu.agh.toik.visualisation.database.dto.Offer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Puszek_SE on 2015-05-27.
 */
@Component
public class OfferService {

    private OfferDAO offerDAO;

    public OfferDAO getOfferDAO() {
        return offerDAO;
    }

    private List<Offer> offers;

    @Autowired
    public void setOfferDAO(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }




    public void addOffer(Offer offer) {
        getOfferDAO().saveOffer(offer);
    }

    public List<Offer> listAllOffers() {
        if(null==offers){
            offers = new LinkedList<Offer>(getOfferDAO().listOffers());
        }
        return offers;
    }
}