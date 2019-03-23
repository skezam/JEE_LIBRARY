

<!DOCTYPE HTML>
<html>
<head>
<title>Title of the document</title>
<link href="maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>

<body>
<div class="container">
	<div class="row">
		<form class="form-horizontal" action="connexion.jsp" method="get">
		    <fieldset>
		        <legend class="" > Login </legend>
		        <div class="control-group">
		        <label class="control-lable" for=Username>Username</label>
                 <div class="controls">
		        <input type="text"  name="username">
		        </div>
		        </div>
		        <div class="control-group">
		        <label class="control-lable" for=Password>Password</label>
		        <div class="controls"> 
		        <input type="text"  name="Password">
		        </div>
		        </div>
		        <br>
		        <div class="control-group">
		         <div class="controls">
			        <button class="btn btn-success">Login</button>
			      </div>
			    </div>
		    </fieldset>
		</form>
	</div>
</div>
</body>

</html>