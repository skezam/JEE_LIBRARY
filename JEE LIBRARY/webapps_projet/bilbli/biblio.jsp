<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "mediatheque.*"%>
<%

	  
Mediatheque m = Mediatheque.getInstance();
Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
List<Document> tousLesDocs=m.tousLesDocuments();

int getIndex = 1;
%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

  <head>
    <meta charset="utf-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Bibliothéque</title>
  </head>
 
  <body>
   <h1> Bibliothé </h1>
  <div class="container">
  <div class="row">
  <div class="col-md-12">
  
	
	<ul class="list-group list-group-flush">

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Titre</th>
      <th scope="col">Auteur</th>
      <th scope="col">Type</th>
	  <!-- <th scope="col">Type</th>-->
    </tr>
  </thead>
  <tbody>
   <% 
	for(Document doc : tousLesDocs){
		
%>
    <tr>
      <th scope="row">1</th>
      <td><%=doc.affiche()[1]%></td>
      <td><%=doc.affiche()[3]%></td>
      <td><%=doc.affiche()[2]%></td>
	   <% 
		} %>
    </tr>
	
	 </td>
	 
  </tbody>
</table>
<form class="form-horizontal" action="ajoutDoc.jsp" method="powt">
  <div class="form-group">
    <label for="Nom">Nom du document</label>
    <input type="text" class="form-control" name="Nom" >
  </div>
  <div class="form-group">
    <label for="auteur">Auteur du document</label>
    <input type="text" class="form-control" name="auteur" >
  </div>
  <div class="form-group">
    <label for="type">Type du Document</label>
    <input type="text" class="form-control" name="type" >
  </div>
</form>
<td><a class="btn btn-primary" href="ajoutDoc.jsp?" role="button">Ajouter</a>
 
 
</ul>
</div>
</div>
</div>
  </body>
</html>