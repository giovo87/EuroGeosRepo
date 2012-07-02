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
public class VerifyLogged extends HttpServlet {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyLogged() {
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
    	if(cookies != null && cookies.length != 0){
    		System.out.println("ho trovato dei cookies...");
    		for (int i = 0; i < cookies.length; i++){
    			System.out.println(cookies[i].getValue());
    			if(request.getSession().getAttribute("token") != null &&
    					request.getSession().getAttribute("token").equals(cookies[i].getValue())){
    				    response.sendRedirect("entryForm.html");
    				    return;
    			}
    		}
    	}
    	response.sendRedirect("login.html");
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
}