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

package it.geosolutions.fao.fra.entryform.table1_4_a.servlets;

import it.geosolutions.fao.fra.entryform.table1_4_a.dao.EntityForestDAO;
import it.geosolutions.fao.fra.entryform.table1_4_a.dao.EntityForestPostgresDAO;
import it.geosolutions.fao.fra.entryform.table1_4_a.entity.EntityForest;
import it.geosolutions.fao.fra.entryform.table1_4_a.entity.PkForest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Gabriele Giovenco
 *
 */

/**
 * Servlet implementation
 */
public class ForestServletUser extends HttpServlet {
    
    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForestServletUser() {
        super();
    }

    /**
     * Performs an HTTP GET request
     * 
     * @param httpServletRequest The request object passed in by the servlet engine representing the client request
     * @param httpServletResponse The response object sent to the client
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> mp=new HashMap<String, String>();
        String userid = null;
        if(request.getSession().getAttribute("map") != null && request.getSession().getAttribute("token") != null){
                mp  = (Map<String, String>) request.getSession().getAttribute("map");
                userid = mp.get(request.getSession().getAttribute("token"));
            
        	EntityForestDAO efd = (EntityForestDAO) new EntityForestPostgresDAO();
            
            List<EntityForest> list = (List<EntityForest>)efd.getDataUser(userid);
            
            JSONArray entities = new JSONArray();
            JSONObject entity;
            Iterator<EntityForest> it = list.iterator();
            EntityForest e;
             
            
            try{
                while(it.hasNext()){
                    entity = new JSONObject();
                    e = it.next();
                    entity.put("year", e.getPkForest().getYear());
                    if(e.getForest() != null)
                        entity.put("forest", e.getForest());
                    else
                        entity.put("forest", "n.a.");
                    
                    if(e.getOther_wooded_land() != null)
                        entity.put("other_wooded_land", e.getOther_wooded_land());
                    else
                        entity.put("other_wooded_land", "n.a.");
                    
                    if(e.getOther_land() != null)
                        entity.put("other_land", e.getOther_land());
                    else
                        entity.put("other_land", "n.a.");
                    
                    if(e.getOther_tree_cover() != null)
                        entity.put("other_tree_cover", e.getOther_tree_cover());
                    else
                        entity.put("other_tree_cover", "n.a.");
                    
                    if(e.getInland_water_bodies() != null)
                        entity.put("inland_water_bodies", e.getInland_water_bodies());
                    else
                        entity.put("inland_water_bodies", "n.a.");
                    
                    entity.put("userid", e.getPkForest().getUserId());
                    entities.put(entity);
                }
            }catch (JSONException jse){}
            
            response.setContentType("application/json");
            response.getWriter().write(entities.toString());
            
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
