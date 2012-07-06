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

package it.geosolutions.fao.fra.entryform.table1_4_b.dao;

import it.geosolutions.fao.fra.entryform.table1_4_b.entity.*;

import java.util.List;


/**
 * @author Gabriele Giovenco
 *
 */

/**
 * This interface must be implemented for each kind of database access
 * 
 */
public interface EntityCategoriesDAO {
    public void update(String user, String param, String category, String value);
    public List<EntityCategories> getDataUser(String user);
}