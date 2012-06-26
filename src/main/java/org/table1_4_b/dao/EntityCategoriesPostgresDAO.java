package org.table1_4_b.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.table1_4_b.entity.EntityCategories;

/**
 * @author Gabriele Giovenco
 *
 */

/**
 * This is an implementation of the EntityForestDAO interface supporting Postgres DB
 * 
 */

public class EntityCategoriesPostgresDAO implements EntityCategoriesDAO{
    
    //create the Entity Manager Factory
    private EntityManagerFactory emf;

    //Get Data User function called from the servlet in order to insert retrieve user's data from the DB
    @SuppressWarnings("unchecked")
    public List<EntityCategories> getDataUser(String user) {
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM EntityCategories e WHERE e.userId =  ?1");
        q.setParameter(1, user);
        return q.getResultList();
    }

    //Update function called from the servlet in order to update a record in the DB
    public void update(String user, String param, String value) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE EntityCategories e SET e." + param + " = ?1 WHERE e.userId = ?2");
        q.setParameter(1, value);
        q.setParameter(2, user);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    //Delete function called from the servlet in order to delete a record in the DB
    public void delete(String userid) {
        // TODO Auto-generated method stub
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("delete from EntityForest e where e.userId= ?1");
        q.setParameter(1, userid);
        q.executeUpdate();
        em.getTransaction().commit();
        
    }
    
}