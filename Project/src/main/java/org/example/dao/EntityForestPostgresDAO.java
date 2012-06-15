package org.example.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

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

    public void update(EntityForest ef) {
        // TODO Auto-generated method stub
        
    }

    public void delete(EntityForest ef) {
        // TODO Auto-generated method stub
        
    }
    
}