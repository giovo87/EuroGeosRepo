package org.example.servlets;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class ForestServletUpdate extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForestServletUpdate() {
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
            
            String user = request.getParameter("userid");
            int year = Integer.parseInt(request.getParameter("year"));
            String param = request.getParameter("param");
            int value = Integer.parseInt(request.getParameter("value"));
            
            efd.update(user, year, param, value);
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
        }

}