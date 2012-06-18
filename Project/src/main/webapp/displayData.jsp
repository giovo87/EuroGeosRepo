<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User's data:</title>
</head>
<body>
	
	<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="1">
	
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="org.example.entity.*" %>
	<%
	
		ArrayList<EntityForest> l = (ArrayList<EntityForest>)request.getAttribute("data");
		Iterator<EntityForest> i = l.iterator();
		EntityForest e;
 		while(i.hasNext()){
 		    e = i.next();
 		    out.println("<tr><td>" + e.getYear() + "</td><td>" + e.getForest() + "</td><td>" + e.getId() + "</td><td>" + e.getInland_water_bodies() + "</td><td>" + e.getOther_land() + "</td><td>" + e.getOther_tree_cover() + "</td><td>" + e.getOther_wooded_land() + "</td><td>" + e.getUserId() + "</td></tr>");
 		}	
	%>
	
	</TABLE>

</body>
</html>