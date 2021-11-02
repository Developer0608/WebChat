<html>
<head>
	<meta charset="utf-8">
	<title>WEBCHAT</title>
	<link rel="icon" href="../../static/pics/logo.jpg" type="image/x-icon">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="../../static/css/setting.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="../../static/js/validate.js"></script>

</head>
<body>
  <br>
  <br>
  <br>
  <section class="py-5 my-5">
		<div class="container">
			<h1 class="mb-5">SETTING</h1>
			<div class="bg-white shadow rounded-lg d-block d-sm-flex">
				<div class="profile-tab-nav border-right">
					<div class="p-4">
						<div class="img-circle text-center mb-3">
							<img id="profileImage"src="../../static/pics/thumb-97920.png" alt="Image" class="shadow">
						</div>
						<input id="imageUpload" type="file" name="profile_photo" placeholder="Photo" required="" capture>
						<h4 class="text-center" id = "loggeduser">User</h4>
					</div>
					<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
						<a class="nav-link active" id="account-tab" data-toggle="pill" href="#account" role="tab" aria-controls="account" aria-selected="true">
							<i class="fa fa-home text-center mr-1"></i>
							Account
						</a>
						<a class="nav-link" id="password-tab" data-toggle="pill" href="#password" role="tab" aria-controls="password" aria-selected="false">
							<i class="fa fa-key text-center mr-1"></i>
							Password
						</a>
						<a class="nav-link" id="security-tab" data-toggle="pill" onclick="logout(event)" role="tab" aria-controls="security" aria-selected="false">
							<i class="fa fa-sign-out text-center mr-1"></i>
							Logout
						</a>
						<a class="nav-link" id="back-tab" onclick="back(event)" data-toggle="pill" role="tab"  aria-selected="false">
							<i class="fa fa-undo text-center mr-1"></i>
							Back
						</a>
						 
					</div>
				</div>
				<div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
					<div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
						<h3 class="mb-4">UPDATE USERNAME</h3>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Username</label>
								  	<input type="text" id = "username" class="form-control">
								</div>
							</div>
							 
						</div>
						<div>
							<button class="btn btn-primary" onclick="updateUsername(event)">Update</button>
							<button class="btn btn-light" onclick="cancel(event)">Cancel</button>
						</div>
					</div>
					<div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
						<h3 class="mb-4">UPDATE PASSWORD</h3>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Old password</label>
								  	<input type="password" id="oldpassword"class="form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  	<label>New password</label>
								  	<input type="password" id="newpassword"class="form-control">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Confirm new password</label>
								  	<input type="password" id="confirmpassword"class="form-control">
								</div>
							</div>
						</div>
						<div>
							<button class="btn btn-primary" onclick="updatePassword(event)">Update</button>
							<button class="btn btn-light" onclick="cancel(event)">Cancel</button>
						</div>
					</div>
			</div>
		</div>
	</section>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   
  <footer>
    <p>
        SAVE WATER, SAVE LIFE
    </p>
</footer>
</body>
<script src="../../static/js/authenticate.js"></script>
<script src="../../static/js/chatapp.js"></script>
<script src="../../static/js/updatepassword.js"></script>
</html>