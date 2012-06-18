package org.example.servlets;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.dao.EntityForestDAO;
import org.example.dao.EntityForestPostgresDAO;
import org.example.entity.EntityForest;

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
            
            //Parameters from request
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Response:</h1>");
            response.getWriter().println("Request received correctly!");
            
            //FIXME: change the DAO creation!!!!!
            EntityForestDAO efd = (EntityForestDAO) new EntityForestPostgresDAO();
            
            //Create an instance of the EntityForest
            //Set entity's parameters
            //Call the method insert() of EntityForestDAO to persist the new entity
            EntityForest ef = new EntityForest();
            
            //ef.setId(Integer.parseInt(request.getParameter("id")));
            ef.setUserId(request.getParameter("userid"));
            ef.setYear(request.getParameter("year"));
            ef.setForest(request.getParameter("forest"));
            ef.setOther_wooded_land(request.getParameter("owl"));
            ef.setOther_land(request.getParameter("ol"));
            ef.setOther_tree_cover(request.getParameter("otc"));
            ef.setInland_water_bodies(request.getParameter("iwb"));
            
            efd.insert(ef);
            
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
        }

}
