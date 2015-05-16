package pl.edu.agh.toik.visualisation.database.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Puszek_SE on 2015-05-13.
 */

@Transactional
public class StreetDAO {

    @Autowired
    private SessionFactory sessionFactory;



    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
