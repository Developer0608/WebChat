<html>
<head>
	<meta charset="utf-8">
	<title>WEBCHAT</title>
	<link rel="stylesheet" href="../../static/css/forget.css">
	<link rel="icon" href="../../static/pics/logo.jpg" type="image/x-icon">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="../../static/js/api.js"></script>
	 
	
</head>
<body>
	<div class="box">
		<h2>ENTER OTP </h2>
		<form action="../../WEB-INF/jsp/OTPPage.jsp">
			<div class="inputBox">
				<input type="text" id= "otp" name="otp" required>
				<label for="">OTP</label>
			</div>

			<input type="submit" name="" onclick="Otp(event)" value="Submit">
		</form>
	</div>
</body>
<script src="../../static/js/forgetpassword.js"></script>
</html>