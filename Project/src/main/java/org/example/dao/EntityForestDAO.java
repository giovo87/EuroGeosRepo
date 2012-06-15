package org.example.dao;

import org.example.entity.EntityForest;


/**
 * This interface must be implemented for each kind of database access
 * 
 */
public interface EntityForestDAO {
    public void insert(EntityForest ef);
    public void update(EntityForest ef);
    public void delete(EntityForest ef);
}
