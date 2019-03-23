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



  String nom = request.getParameter("Nom");
  String auteur = request.getParameter("auteur");
	int type= Integer.parseInt(request.getParameter("type"));
  int idDoc = Integer.parseInt(request.getParameter("IdDocument"));




		m.nouveauDocument(idDoc,type,nom,auteur,utilisateur);
		response.sendRedirect("bilbio.jsp");

    %>
  </body>
</html>