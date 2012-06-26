package org.table1_4_b.servlets;

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
import org.table1_4_b.dao.EntityCategoriesDAO;
import org.table1_4_b.dao.EntityCategoriesPostgresDAO;
import org.table1_4_b.entity.EntityCategories;

/**
 * @author Gabriele Giovenco
 *
 */

/**
 * Servlet implementation class ForestServlet
 */
public class CategoriesServletUser extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServletUser() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
            
            //Get parameter from request, call get data function and prepare the json object
            EntityCategoriesDAO efd = (EntityCategoriesDAO) new EntityCategoriesPostgresDAO();
            
            List<EntityCategories> list = (List<EntityCategories>)efd.getDataUser(request.getParameter("userid"));

            JSONArray entities = new JSONArray();
            JSONObject entity;
            Iterator<EntityCategories> it = list.iterator();
            EntityCategories e = it.next();
            
            try{
                    entity = new JSONObject();
                    entity.put("forest", e.getForest());
                    entity.put("other_wooded_land", e.getOther_wooded_land());
                    entity.put("other_land", e.getOther_land());
                    entity.put("other_tree_cover", e.getOther_land());
                    entity.put("inland_water_bodies", e.getInland_water_bodies());
                    entities.put(entity);
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
