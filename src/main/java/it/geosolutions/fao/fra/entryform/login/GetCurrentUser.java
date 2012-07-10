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

package it.geosolutions.fao.fra.entryform.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
public class GetCurrentUser extends HttpServlet {
    
    private String userDb;
    private String passwdDb;
    private String urlDb;
    private Connection dbcon;
    
    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurrentUser() {
        super();
    }

    /**
     * Performs an HTTP GET request
     * 
     * @param httpServletRequest The request object passed in by the servlet engine representing the client request
     * @param httpServletResponse The response object sent to the client
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String userName = "";
        if(cookies != null && cookies.length != 0){
            for (int i = 0; i < cookies.length; i++){
                if(request.getSession().getAttribute("token") != null &&
                                request.getSession().getAttribute("token").equals(cookies[i].getValue())){
                    Map<String,String> mp=new HashMap<String, String>();
                    mp  = (Map<String, String>) request.getSession().getAttribute("map");
                    userName = retrieveUserName(mp.get(request.getSession().getAttribute("token")));
                }
            }
        }
        response.getWriter().print(userName);
    }
    
    
    /**
     * Set up parameters for db connection
     * 
     */
    public void init() throws ServletException {
        userDb = getServletConfig().getInitParameter("userDb");
        passwdDb = getServletConfig().getInitParameter("passwdDb");
        urlDb = getServletConfig().getInitParameter("urlDb");
        try 
        {
              Class.forName("org.postgresql.Driver");
              dbcon = DriverManager.getConnection(urlDb, userDb, passwdDb);
        }
        catch (ClassNotFoundException ex)
        {
               System.err.println("ClassNotFoundException: " + ex.getMessage());
               throw new ServletException("Class not found Error");
        }
        catch (SQLException ex)
        {
               System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    /**
     * Function called in order to retrieve the id of the user
     * 
     * @param username The request object passed in by the servlet engine representing the client request
     * @param password The response object sent to the client
     */
    private String retrieveUserName(String id){
        try
        {
                Statement statement = dbcon.createStatement();
                String query = "SELECT username ";
                query +=       "FROM users ";
                query +=       "WHERE id=\'"+id+"\'";
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                return rs.getString("username");
        }
        catch(Exception ex)
        {
                System.out.println("Error..."+ex.getMessage());
                return null;
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
    

}