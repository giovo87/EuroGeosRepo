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

package org.table1_4_a.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.table1_4_a.dao.EntityForestDAO;
import org.table1_4_a.dao.EntityForestPostgresDAO;
import org.table1_4_a.entity.EntityForest;

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
        
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Response:</h1>");
        response.getWriter().println("Request received correctly!");
        
        EntityForestDAO efd = (EntityForestDAO) new EntityForestPostgresDAO();
        
        EntityForest ef = new EntityForest();
        
        ef.setUserId(request.getParameter("userid"));
        ef.setYear(Integer.parseInt(request.getParameter("year")));
        ef.setForest(Integer.parseInt(request.getParameter("forest")));
        ef.setOther_wooded_land(Integer.parseInt(request.getParameter("owl")));
        ef.setOther_land(Integer.parseInt(request.getParameter("ol")));
        ef.setOther_tree_cover(Integer.parseInt(request.getParameter("otc")));
        ef.setInland_water_bodies(Integer.parseInt(request.getParameter("iwb")));
        
        efd.insert(ef);
        
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
