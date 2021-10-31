<html>
<head>
	<meta charset="utf-8">
	<title>WEBCHAT</title>
	<link rel="stylesheet" href="../../static/css/forget.css">
	<link rel="icon" href="../../static/pics/logo.jpg" type="image/x-icon">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<div class="box">
		<h2>FOR RECOVERY LINK </h2>
		<form action="../../WEB-INF/jsp/OTPPage.jsp">
			<div class="inputBox">
				<input type="email" id= "email" name="email" required>
				<label for="">Email</label>
			</div>

			<input type="submit" name="" onclick="forget(event)" value="Submit">
		</form>
	</div>
</body>
<script src="../../static/js/forgetpassword.js"></script>
</html>