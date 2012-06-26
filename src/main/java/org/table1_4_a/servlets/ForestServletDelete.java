package org.table1_4_a.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class ForestServletDelete extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForestServletDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
            
            //Get parameters from request and call delete function
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Response:</h1>");
            response.getWriter().println("Request received correctly!");
            
            EntityForestDAO efd = (EntityForestDAO) new EntityForestPostgresDAO();
            
            int year = Integer.parseInt(request.getParameter("year"));
            String userid = request.getParameter("userid");
            efd.delete(userid, year);
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
        }

}