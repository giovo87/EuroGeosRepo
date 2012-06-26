package org.table1_4_a.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.table1_4_a.entity.EntityForest;

/**
 * @author Gabriele Giovenco
 *
 */

/**
 * This is an implementation of the EntityForestDAO interface supporting Postgres DB
 * 
 */

public class EntityForestPostgresDAO implements EntityForestDAO{
    
    //create the Entity Manager Factory
    private EntityManagerFactory emf;

    //Insert function called from the servlet in order to insert a new record in the DB
    public void insert(EntityForest ef) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ef);
        em.getTransaction().commit();
    }

    //Get Data User function called from the servlet in order to insert retrieve user's data from the DB
    @SuppressWarnings("unchecked")
    public List<EntityForest> getDataUser(String user) {
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM EntityForest e WHERE e.userId =  ?1");
        q.setParameter(1, user);
        return q.getResultList();
    }

    //Update function called from the servlet in order to update a record in the DB
    public void update(String user, int year, String param, int value) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE EntityForest e SET e." + param + " = ?1 WHERE e.year = ?2 AND e.userId = ?3");
        q.setParameter(1, value);
        q.setParameter(2, year);
        q.setParameter(3, user);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    //Delete function called from the servlet in order to delete a record in the DB
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