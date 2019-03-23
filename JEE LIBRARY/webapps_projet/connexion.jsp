<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "mediatheque.*"%>
 
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Connexion</title>
  </head>
 
  <body>
 
    <%
      try {
        Class.forName("persistantdata.MediathequeData");
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      Mediatheque m = Mediatheque.getInstance();
      String login = request.getParameter("username");
      String password = request.getParameter("Password");
      Utilisateur utilisateur = m.getUser(login, password);
 
      if (null == utilisateur) {
        response.sendRedirect("index.jsp?login=failed");
        return;
      }
 
      session.setAttribute("login", login);
      session.setAttribute("password", password);
      session.setAttribute("mediatheque", m);
     
     if (utilisateur.isBibliothecaire()) response.sendRedirect("bilbli/biblio.jsp");
      else response.sendRedirect("utilisateur/utilisateur.jsp");
    %>
  </body>
</html>