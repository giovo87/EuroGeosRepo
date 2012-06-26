package org.table1_4_b.dao;

import java.util.List;
import org.table1_4_b.entity.*;


/**
 * @author Gabriele Giovenco
 *
 */

/**
 * This interface must be implemented for each kind of database access
 * 
 */
public interface EntityCategoriesDAO {
    public void update(String user, String param, int value);
    public void delete(String userid);
    public List<EntityCategories> getDataUser(String user);
}