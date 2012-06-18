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
public class ForestServletUser extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForestServletUser() {
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
            response.getWriter().println("<h1>Utente:</h1>");
            response.getWriter().println(request.getParameter("userid"));
            
            //FIXME: change the DAO creation!!!!!
            EntityForestDAO efd = (EntityForestDAO) new EntityForestPostgresDAO();
            
            List<EntityForest> list = (List<EntityForest>)efd.getDataUser(request.getParameter("userid"));

            request.setAttribute("data", list);
            
            getServletConfig().getServletContext().getRequestDispatcher("/displayData.jsp").forward(request, response);
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
        }

}
