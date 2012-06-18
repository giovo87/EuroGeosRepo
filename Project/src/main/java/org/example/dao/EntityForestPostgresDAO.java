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

    public void update(EntityForest ef, int id) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE EntityForest e SET e.year = ?1, e.forest = ?2, e.other_wooded_land = ?3, e.other_land = ?4, e.other_tree_cover = ?5, e.inland_water_bodies = ?6 WHERE e.id =  ?7");
        q.setParameter(1, ef.getYear());
        q.setParameter(2, ef.getForest());
        q.setParameter(3, ef.getOther_wooded_land());
        q.setParameter(4, ef.getOther_land());
        q.setParameter(5, ef.getOther_tree_cover());
        q.setParameter(6, ef.getInland_water_bodies());
        q.setParameter(7, id);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    //FIXME: da testare
    public void delete(int id) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("delete from EntityForest e where e.id = ?1");
        q.setParameter(1, id);
        q.executeUpdate();
        em.getTransaction().commit();
        
    }
    
}