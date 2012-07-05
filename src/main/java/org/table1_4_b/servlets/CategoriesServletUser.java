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

package org.table1_4_b.servlets;

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
import org.table1_4_b.dao.EntityCategoriesDAO;
import org.table1_4_b.dao.EntityCategoriesPostgresDAO;
import org.table1_4_b.entity.EntityCategories;

/**
 * @author Gabriele Giovenco
 *
 */

/**
 * Servlet implementation
 */
public class CategoriesServletUser extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServletUser() {
        super();
    }

    /**
     * Performs an HTTP GET request
     * 
     * @param httpServletRequest The request object passed in by the servlet engine representing the client request
     * @param httpServletResponse The response object sent to the client
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("map") != null && request.getSession().getAttribute("token") != null){
            Map<String,String> mp=new HashMap<String, String>();
            mp  = (Map<String, String>) request.getSession().getAttribute("map");
            String userid = mp.get(request.getSession().getAttribute("token"));
            
            EntityCategoriesDAO efd = (EntityCategoriesDAO) new EntityCategoriesPostgresDAO();
            
            List<EntityCategories> list = (List<EntityCategories>)efd.getDataUser(userid);
    
            JSONArray entities = new JSONArray();
            JSONObject entity;
            Iterator<EntityCategories> it = list.iterator();
            EntityCategories e;
            
            try{
                while(it.hasNext()){
                    e = it.next();
                    entity = new JSONObject();
                    entity.put("category", e.getPkCategory().getCategory());
                    entity.put("tier_for_reported_trend", e.getTier_for_reported_trend());
                    entity.put("tier_for_status", e.getTier_for_status());
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
