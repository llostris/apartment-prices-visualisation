package pl.edu.agh.toik.visualisation.database.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.toik.visualisation.database.dto.Offer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Puszek_SE on 2015-05-16.
 */

@Repository
@Transactional
public class OfferDAO {

    Logger logger = Logger.getLogger(OfferDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void saveOffer(Offer offer){
        entityManager.persist(offer);
        entityManager.flush();
        logger.info("Saved: "+offer);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
