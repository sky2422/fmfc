<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>FMFC</title>
<style type="text/css">
body {
	height: 100vh;
	background-image: url(images/logo/main.jpg);
	background-repeat: no-repeat;
	background-size: cover;
	text-align: center;
}

.wrap {
	display: grid;
	place-items: center;
}

.wrap img {
	font-family: system-ui, serif;
	font-size: 2rem;
	padding: 3rem;
	border-radius: 1rem;
	margin-top:15%;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
</head>
<body>
	<div class="wrap">
		<a href="customerMain.jsp"><img src="images/logo/fmfc.png"></a>
	</div>
</body>
</html>