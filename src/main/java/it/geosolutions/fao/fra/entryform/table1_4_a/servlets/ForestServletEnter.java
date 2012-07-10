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
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Gabriele Giovenco
 *
 */

/**
 * Servlet implementation
 */
public class ForestServletEnter extends HttpServlet {
    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForestServletEnter() {
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
            
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Response:</h1>");
            response.getWriter().println("Request received correctly!");
            
            EntityForestDAO efd = (EntityForestDAO) new EntityForestPostgresDAO();
            
            EntityForest ef = new EntityForest();
            
            PkForest pk = new PkForest(userid, Float.parseFloat(request.getParameter("year")));
            ef.setPkForest(pk);
            if(!request.getParameter("forest").equals(""))
                ef.setForest(Float.parseFloat(request.getParameter("forest")));
            else
                ef.setForest(null);
            if(!request.getParameter("owl").equals(""))
                ef.setOther_wooded_land(Float.parseFloat(request.getParameter("owl")));
            else
                ef.setOther_wooded_land(null);
            if(!request.getParameter("ol").equals(""))
                ef.setOther_land(Float.parseFloat(request.getParameter("ol")));
            else
                ef.setOther_land(null);
            if(!request.getParameter("otc").equals(""))
                ef.setOther_tree_cover(Float.parseFloat(request.getParameter("otc")));
            else
                ef.setOther_tree_cover(null);
            if(!request.getParameter("iwb").equals(""))
                ef.setInland_water_bodies(Float.parseFloat(request.getParameter("iwb")));
            else
                ef.setInland_water_bodies(null);
            efd.insert(ef);
        }
        
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
