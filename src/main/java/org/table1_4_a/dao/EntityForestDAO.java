package org.table1_4_a.dao;

import java.util.List;

import org.table1_4_a.entity.EntityForest;

/**
 * @author Gabriele Giovenco
 *
 */

/**
 * This interface must be implemented for each kind of database access
 * 
 */
public interface EntityForestDAO {
    public void insert(EntityForest ef);
    public void update(String user, int year, String param, int value);
    public void delete(String userid, int year);
    public List<EntityForest> getDataUser(String user);
}
