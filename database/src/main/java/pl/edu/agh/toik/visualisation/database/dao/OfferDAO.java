package pl.edu.agh.toik.visualisation.database.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.toik.visualisation.database.dto.Offer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Puszek_SE on 2015-05-16.
 */

@Repository("offerDAO")
@Transactional
public class OfferDAO {

    Logger logger = Logger.getLogger(OfferDAO.class);

    private final String selectAll = "SELECT offer FROM pl.edu.agh.toik.visualisation.database.dto.Offer offer";


    @PersistenceContext
    private EntityManager entityManager;

    public void saveOffer(Offer offer){
        entityManager.persist(offer);
        entityManager.flush();
        logger.info("Saved: "+offer);
    }

    public List<Offer> listOffers(){
        Query offersQuery = entityManager.createQuery(selectAll);
        return offersQuery.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
