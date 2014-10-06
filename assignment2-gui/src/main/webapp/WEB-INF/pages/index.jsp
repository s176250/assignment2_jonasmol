<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href=ico/favicon.png">
<title>Student system</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<!-- Static navbar -->
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Student
					system</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/student">Students</a></li>
					<li><a href="${pageContext.request.contextPath}/course">Courses</a></li>
					<li><a href="${pageContext.request.contextPath}/degree">Degrees</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a
						href="${pageContext.request.contextPath}/init">Init database</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	
	<div class="container">
	This is the web interface for the student system assignment of the INF5750 course. 
	You are supposed to implement the hibernate model and test classes, and deploy these in a separate project. 
	You should not change the API or GUI projects. <p/>
	Happy coding! 
	</div>
	
	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    
</body>
</html>