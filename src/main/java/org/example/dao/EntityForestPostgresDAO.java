package org.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.example.entity.EntityForest;

/**
 * This is an implementation of the EntityForestDAO interface supporting Postgres DB
 * 
 */

public class EntityForestPostgresDAO implements EntityForestDAO{
    
    //Annotation
    //@PersistenceUnit(unitName = "eurogeos-unit")
    private EntityManagerFactory emf;

    public void insert(EntityForest ef) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ef);
        em.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
    public List<EntityForest> getDataUser(String user) {
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM EntityForest e WHERE e.userId =  ?1");
        q.setParameter(1, user);
        return q.getResultList();
    }

    public void update(String user, int year, String param, int value) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE EntityForest e SET e." + param + " = ?1 WHERE e.year = ?2 AND e.userId = ?3");
        q.setParameter(1, value);
        q.setParameter(2, year);
        q.setParameter(3, user);
        //q.setParameter(8, ef.getUserId());
        q.executeUpdate();
        em.getTransaction().commit();
    }

    //FIXME: da testare
    public void delete(String userid, int year) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("delete from EntityForest e where e.year = ?1 AND e.userId= ?2");
        q.setParameter(1, year);
        q.setParameter(2, userid);
        q.executeUpdate();
        em.getTransaction().commit();
        
    }
    
}