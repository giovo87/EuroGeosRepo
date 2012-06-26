package org.table1_4_a.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
            
            //Get parameter from request, call get data function and prepare the json object
            EntityForestDAO efd = (EntityForestDAO) new EntityForestPostgresDAO();
            
            List<EntityForest> list = (List<EntityForest>)efd.getDataUser(request.getParameter("userid"));

            JSONArray entities = new JSONArray();
            JSONObject entity;
            Iterator<EntityForest> it = list.iterator();
            EntityForest e;
            
            try{
                while(it.hasNext()){
                    entity = new JSONObject();
                    e = it.next();
                    entity.put("year", e.getYear());
                    entity.put("forest", e.getForest());
                    entity.put("other_wooded_land", e.getOther_wooded_land());
                    entity.put("other_land", e.getOther_land());
                    entity.put("other_tree_cover", e.getOther_tree_cover());
                    entity.put("inland_water_bodies", e.getInland_water_bodies());
                    entity.put("userid", e.getUserId());
                    entities.put(entity);
                }
            }catch (JSONException jse){}
            
            response.setContentType("application/json");
            response.getWriter().write(entities.toString());
            
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
        }

}
