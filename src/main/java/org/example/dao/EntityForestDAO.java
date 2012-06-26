package org.example.dao;


import java.util.List;

import org.example.entity.EntityForest;


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
