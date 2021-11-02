//function to perform operations related to forget password 
function forget(event){
	event.preventDefault();
	const otp_mail = document.getElementById("email").value;
	console.log(otp_mail);

	if(otp_mail == ""){
		swal("OOPS!!!!!!", "Fill Up all the Columns", "warning");
		return;
	}
	fetch("http://localhost:8086/send-otp", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			email: otp_mail 
		})
	}).then(data => data).then(res => {
		if (res.status == 200) {
			swal({
				position: 'top-end',
				icon: 'success',
				title: 'OTP Sent Successfully',
				showConfirmButton: false,
				timer: 1500
			  })

			setTimeout(()=>{
				window.open(`otp?email=${otp_mail}`, "_self");
			},1500)
		} else {
			swal("OOPS!!!!!!", "Email is not Registered with Us", "error");
		}

	}).catch(err => {
		swal("OOPS!!!!!!", "Account Does not Exist", "error");
	})
}


function Otp(event){
	event.preventDefault();
	
	console.log('I am in OTP');

	const user_otp = document.getElementById('otp').value;
	console.log(user_otp);

	const url = new URL(window.location.href);
	const email = url.searchParams.get("email");

	if (email === '' || email === null) {
		console.log("######## SPECIFY EMAIL")
		window.location.replace('/authenticate');
		return;
	}

	console.log(">>>>>>>>>>>", email);

	if(user_otp == ""){
		swal("OOPS!!!!!!", "Please Enter the OTP", "warning");
		return;
	}
	fetch("http://localhost:8086/check-otp", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			otp: user_otp,
			email: email
		})
	}).then(data => data).then(res => {
		if (res.status == 200) {
			window.open(`setpassword?email=${email}`, "_self");		
		} else {
			swal("OOPS!!!!!!", "Incorrect OTP", "error");
		}
	
	}).catch(err => {
		swal("OOPS!!!!!!", "Incorrect OTP", "error");
	})
	 
}

function setpassword(event){
	event.preventDefault();

	console.log("I am in setpassword");

	const password = document.getElementById('newpassword').value;
	const confirm_password = document.getElementById('confirmpassword').value;

	const url = new URL(window.location.href);
	const email = url.searchParams.get("email");

	console.log(password, confirm_password);
	if(password == ""){
		swal("OOPS!!!!!!", "Fill the Columns", "error");
		return;
	}

	if(password != confirm_password){
		swal("OOPS!!!!!!", "Password Mis-match", "warning");
		return;
	}

	fetch("http://localhost:8086/setpassword", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			password: password,
			email: email
		})
	}).then(data => data).then(res => {
		if (res.status == 200) {
			 swal("Great", "Password Reset Successfully", "success");
			 setTimeout(()=>{
				window.open("/", "_self")		 
			},1000)	
			  
		} else {
			swal("OOPS!!!!!!", "Password Reset Failed", "error");
		}
	
	}).catch(err => {
		swal("OOPS!!!!!!", "Password Reset Failed", "error");
	})
}
