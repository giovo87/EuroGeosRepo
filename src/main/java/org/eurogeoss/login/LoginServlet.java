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

package org.eurogeoss.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
public class LoginServlet extends HttpServlet {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String userDb;
    private String passwdDb;
    private String urlDb;
    private Connection dbcon;
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * Performs an HTTP GET request
     * 
     * If an user submit a login request, if exists, the previous informations are deleted and a new login will be performed
     * 
     * @param httpServletRequest The request object passed in by the servlet engine representing the client request
     * @param httpServletResponse The response object sent to the client
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String user = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(scramble(password));
        if(checkInDb(user, password)){
            Cookie[] cookies = request.getCookies();
            if(cookies != null && cookies.length != 0){
                    for (int i = 0; i < cookies.length; i++){
                            if(request.getSession().getAttribute("token") != null &&
                                            request.getSession().getAttribute("token").equals(cookies[i].getValue())){
                                cookies[i].setMaxAge(-1);
                                request.getSession().removeAttribute("token");
                                request.getSession().removeAttribute("map");
                                
                            }
                    }
            }
            Map<String,String> mp=new HashMap<String, String>();
            Random rand = new Random();
            String token = Long.toString(rand.nextLong());
            request.getSession().setAttribute("token", token);
            Cookie userCookie = new Cookie("session", token);
            userCookie.setMaxAge(600);
            response.addCookie(userCookie);
            mp.put(token, retrieveUserID(user, password));
            request.getSession().setAttribute("map", mp);
            response.setStatus(response.SC_OK);
            return;
        }
        response.setStatus(response.SC_UNAUTHORIZED);
        return;
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
    
    /**
     * Set up parameters for db connection
     * 
     */
    public void init() throws ServletException {
        userDb = "postgres";
        passwdDb = "postgres";
        urlDb = "jdbc:postgresql://localhost:5432/eurogeos";
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
     * Function called in order to check if the given data is in the users table
     * 
     * @param username The request object passed in by the servlet engine representing the client request
     * @param password The response object sent to the client
     */
    private boolean checkInDb(String username, String password){
        try
        {
                Statement statement = dbcon.createStatement();
                String query = "SELECT username, password ";
                query +=       "FROM users ";
                query +=       "WHERE username=\'"+username+"\' AND password=\'"+password+"\'";
                ResultSet rs = statement.executeQuery(query);
                return rs.next();

        }
        catch(Exception ex)
        {
                System.out.println("Error..."+ex.getMessage());
                return false;
        }
    }
    
    
    /**
     * Function called in order to retrieve the id of the user
     * 
     * @param username The request object passed in by the servlet engine representing the client request
     * @param password The response object sent to the client
     */
    private String retrieveUserID(String username, String password){
        try
        {
                Statement statement = dbcon.createStatement();
                String query = "SELECT id ";
                query +=       "FROM users ";
                query +=       "WHERE username=\'"+username+"\' AND password=\'"+password+"\'";
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                return rs.getString("id");
        }
        catch(Exception ex)
        {
                System.out.println("Error..."+ex.getMessage());
                return null;
        }
    }
    
    /**
    * SHA-1 Cryptographic hash algorithm
    * 
    * @param text  password to digest
    * @return      the hexadecimal encoded string
    */
    public static String scramble(String text)
    {
            try {
                    MessageDigest md = MessageDigest.getInstance("SHA-1") ;
                    md.update(text.getBytes("UTF-8"));
                    return getHex(md.digest());
            }
            catch (UnsupportedEncodingException e) { return null; }
            catch (NoSuchAlgorithmException e)     { return null; }
    }
    
    /**
    * Convert byte array in hexadecimal encoded string
    * 
    * @param raw
    * @return the hexadecimal encoded string
    */
    private static final String HEXES = "0123456789abcdef";
    private static String getHex(byte[] raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
            HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }
    
}