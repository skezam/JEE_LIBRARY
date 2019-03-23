<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "mediatheque.*"%>
<%
	  
Mediatheque m = Mediatheque.getInstance();
Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
List<Document> tousLesDocs = m.tousLesDocuments();

int getIndex = 1;
%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

  <head>
    <meta charset="utf-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Abonné</title>
  </head>
 
  <body>
  	

  <center><h1>Abonné </h1></center>
   
		
		
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
	  <th scope="col">User</th>
	  <th scope="col">Actions</th>
	  <!-- <th scope="col">Type</th>-->
    </tr>
  </thead>
  <tbody>
   <% 
	for(Document doc : tousLesDocs){
		
%>
    <tr>
      
      <td><%=doc.affiche()[0]%></td>
      <td><%=doc.affiche()[1]%></td>
      <td><%=doc.affiche()[2]%></td>
	  <td><%=doc.affiche()[3]%></td>
	  <td><%=doc.affiche()[4]%></td>

	 <td><a class="btn btn-primary" href="emprunter.jsp?getIndex=<%=getIndex%>" role="button">Emprunter</a>
	 <a class="btn btn-primary" href="retour.jsp?getIndex=<%=getIndex%>" role="button">Retour</a>
	 </td>
	  <% 
		getIndex++;
		 
		} %>
		</tr>
  </tbody>
</table>

 
</ul>
</div>
</div>
</div>
  </body>
</html>