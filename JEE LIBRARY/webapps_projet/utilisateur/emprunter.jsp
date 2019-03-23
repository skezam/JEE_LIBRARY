<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "mediatheque.*"%>



<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Emprunter</title>
  </head>
 
  <body>
 
    <%
    Mediatheque m = Mediatheque.getInstance();
      String login = (String) session.getAttribute("login");
  		String password = (String) session.getAttribute("password");
    Utilisateur utilisateur = m.getUser(login, password);
    
    int index = Integer.parseInt(request.getParameter("getIndex"));
    Document doc = m.getDocument(index);

    doc.emprunter(utilisateur);
    response.sendRedirect("utilisateur.jsp");
    %>
  </body>
</html>