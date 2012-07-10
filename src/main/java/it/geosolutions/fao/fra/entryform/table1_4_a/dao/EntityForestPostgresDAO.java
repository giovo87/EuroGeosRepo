/*
 *  Copyright (C) 2007 - 2011 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 *  GPLv3 + Classpath exception
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.geosolutions.fao.fra.entryform.table1_4_a.dao;

import it.geosolutions.fao.fra.entryform.table1_4_a.entity.EntityForest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * @author Gabriele Giovenco
 *
 */

/**
 * This is an implementation of the EntityForestDAO interface supporting Postgres DB
 * 
 */

public class EntityForestPostgresDAO implements EntityForestDAO {

    /**
     * The Entity Manager Factory used to create the Entity Manager
     */
    private EntityManagerFactory emf;

    /**
     * Insert function that puts new information in the database
     * 
     * @param ef The Entity passed in by in order to put new information in the DB
     */
    public void insert(EntityForest ef) {
        emf = Persistence.createEntityManagerFactory("eurogeoss-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ef);
        em.getTransaction().commit();
    }

    /**
     * Function that retrieve all user's information
     * 
     * @param user The user id of the user that want retrieve data from database
     */
    @SuppressWarnings("unchecked")
    public List<EntityForest> getDataUser(String user) {
        emf = Persistence.createEntityManagerFactory("eurogeoss-unit");
        EntityManager em = emf.createEntityManager();
        System.out.println(user);
        Query q = em.createQuery("SELECT e FROM EntityForest e WHERE e.pkForest.userId =  ?1");
        q.setParameter(1, user);
        return q.getResultList();
    }

    /**
     * Function that update an entry in the database
     * 
     * @param user The user id of the user that want update data in the database
     * @param year The year of the data that must be updated
     * @param param The name of the parameter that must be updated
     * @param value The value of the parameter that must be updated
     */
    public void update(String user, Float year, String param, Float value) {
        emf = Persistence.createEntityManagerFactory("eurogeoss-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE EntityForest e SET e." + param
                + " = ?1 WHERE e.pkForest.year = ?2 AND e.pkForest.userId = ?3");
        q.setParameter(1, value);
        q.setParameter(2, year);
        q.setParameter(3, user);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    /**
     * Function that delete an entry in the database
     * 
     * @param userid The user id of the user that want delete data in the database
     * @param year The year of the data that must be updated deleted
     */
    public void delete(String userid, Float year) {
        emf = Persistence.createEntityManagerFactory("eurogeoss-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("delete from EntityForest e where e.pkForest.year = ?1 AND e.pkForest.userId= ?2");
        q.setParameter(1, year);
        q.setParameter(2, userid);
        q.executeUpdate();
        em.getTransaction().commit();

    }

}