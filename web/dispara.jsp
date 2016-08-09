
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Paragraph</title>
    </head>
    <body>
        <form action="CustomerDetails" method="post"> 
        <br><br><br></br>

              <!-- <table>
         <%
                ArrayList<String> posts=(ArrayList<String>) request.getAttribute("dispara"); 
                for (int i=0;i<posts.size();i++) {  %> 
                <%=posts.get(1)%>
           <%}%> 
            </table> -->
           <center>
               <table>
                <%
                ArrayList<String> posts1=(ArrayList<String>) request.getAttribute("dispara"); 
                 %> 
                <%=posts1.get(1)%> 
               
                <%
                    
                    session.setAttribute("pid",posts1.get(0));  
                
                %>
               </table>
           </center>
                
            <br><br><br><br>
            <center><input type="submit" value="Submit"/></center>
            
          
       <form>
      
           
    </body>
</html>