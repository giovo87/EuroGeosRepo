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
 * Servlet implementation class ForestServlet
 */
public class ForestServletEnter extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForestServletEnter() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
            
            //Get parameters from request and call insert function
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Response:</h1>");
            response.getWriter().println("Request received correctly!");
            
            EntityForestDAO efd = (EntityForestDAO) new EntityForestPostgresDAO();
            
            //Create an instance of the EntityForest
            //Set entity's parameters
            //Call the method insert() of EntityForestDAO to persist the new entity
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

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
        }

}
