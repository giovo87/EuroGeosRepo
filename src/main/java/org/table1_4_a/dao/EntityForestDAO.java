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
