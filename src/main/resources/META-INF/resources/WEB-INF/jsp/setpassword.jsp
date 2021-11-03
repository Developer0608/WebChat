<!DOCTYPE html>
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
		<h2>SET PASSWORD</h2>
		<form action="">
			<div class="inputBox">
				<input type="password" id="newpassword" name="New Password" required="">
				<label for="">New Password</label>
			</div>
            
            <br>
            <div class="inputBox">
				<input type="password" id = "confirmpassword" name="Confirm Password" required="">
				<label for="">Confirm Password</label>
			</div>
		
			<input type="submit" name="" onclick="setpassword(event)" value="Submit">
		</form>
	</div>
</body>
<script src="../../static/js/forgetpassword.js"></script>
</html>