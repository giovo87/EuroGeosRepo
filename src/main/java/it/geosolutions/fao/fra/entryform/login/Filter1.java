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
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gabriele Giovenco
 *
 */

public class Filter1 implements Filter {
    
    
    /**
    * destroy() : destroy() method called when the filter is taken 
    * out of service.
    */
    public void destroy() {
        System.out.println("Filter destroyed!");
    }
 
    
    /**
     * Performs an HTTP GET request
     * 
     * @param request The request object passed in by the servlet engine representing the client request
     * @param response The response object sent to the client
     * @param chain Object that pass the request and the response to the next element of the chain
     */
    public void doFilter(final ServletRequest request,final ServletResponse response,
            final FilterChain chain ) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        if(httpRequest.getRequestURL().toString().contains("login.html")||
        		httpRequest.getRequestURL().toString().contains("entryForm.html")||
        		httpRequest.getRequestURL().toString().contains("forestUser")||
        		httpRequest.getRequestURL().toString().contains("forestUpdate")||
        		httpRequest.getRequestURL().toString().contains("forestDelete")||
        		httpRequest.getRequestURL().toString().contains("forestEnter")||
        		httpRequest.getRequestURL().toString().contains("categoriesUpdate")||
        		httpRequest.getRequestURL().toString().contains("categoriesUser")){
                Cookie[] cookies = httpRequest.getCookies();
        	if(cookies != null && cookies.length != 0){
        		for (int i = 0; i < cookies.length; i++){
        			if(httpRequest.getSession().getAttribute("token") != null &&
        					httpRequest.getSession().getAttribute("token").equals(cookies[i].getValue())){
        			    if(httpRequest.getRequestURL().toString().contains("login.html")){
        			        httpResponse.sendRedirect("entryForm.html");
        			        return;
        			    }
        			    System.out.println("(1)"+httpRequest.getRequestURL().toString());
        			    chain.doFilter(httpRequest, httpResponse);
        			    return;
        			}
        		}
        	}
        	if(!httpRequest.getRequestURL().toString().contains("login.html")){
        	    System.out.println("(2)"+httpRequest.getRequestURL().toString());
        	    httpResponse.sendRedirect("login.html");
        	    return;
        	}
        }
        System.out.println("(3)"+httpRequest.getRequestURL().toString());
        chain.doFilter(httpRequest, httpResponse);
    }
    

    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
}