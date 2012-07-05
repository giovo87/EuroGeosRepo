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
    
    /**
     * The Entity Manager Factory used to create the Entity Manager
     */
    private EntityManagerFactory emf;

    /**
     * Function that retrieve all user's information
     * 
     * @param user The user id of the user that want retrieve data from database 
     */
    @SuppressWarnings("unchecked")
    public List<EntityCategories> getDataUser(String user) {
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM EntityCategories e WHERE e.pkCategory.userId =  ?1");
        q.setParameter(1, user);
        return q.getResultList();
    }

    /**
     * Function that update an entry in the database
     * 
     * @param user The user id of the user that want update data in the database 
     * @param param The name of the parameter that must be updated
     * @param category The name of the category that must be updated
     * @param value The value of the parameter that must be updated
     */
    public void update(String user, String param, String category, String value) {
        emf = Persistence.createEntityManagerFactory("eurogeos-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE EntityCategories e SET e." + param + " = ?1 WHERE e.pkCategory.userId = ?2 AND e.pkCategory.category = ?3");
        q.setParameter(1, value);
        q.setParameter(2, user);
        q.setParameter(3, category);
        q.executeUpdate();
        em.getTransaction().commit();
    }

}