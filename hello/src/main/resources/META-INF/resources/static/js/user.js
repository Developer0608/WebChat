const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

function login(event) {
	event.preventDefault();

	const email = document.getElementById("login_email").value;
	const password = document.getElementById("login_password").value;

	console.log(email, password);

	if(email == "" || password == ""){
		swal("OOPS!!!!!!", "Fill Up all the Columns", "warning");
		return;
	}

	fetch("http://localhost:8086/login", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			email: email, 
			password: password, 
			username: ""})
	})
	.then(data => {
		if (data.status == 200) {
			return data.json();
		} else {
			swal("OOPS!!!!!!", "Username or Password Incorrect", "error");
			return;
		}
	})
	.then(res => {
		if(res) {
			swal("Great!", "LoggedIn Successfully", "success");
			
			localStorage.setItem('loggedUser', JSON.stringify(res));
			console.log(res);
			setTimeout(()=>{
				window.open('chatpage', "_self");
			},500);
		}
	})
	.catch(err => {
		swal("OOPS!!!!!!", "Account Does't Exist", "error");
	})
}

function signup(event){
	event.preventDefault();

	const username = document.getElementById("name").value;
	const user_email = document.getElementById("email").value;
	const user_password = document.getElementById("password").value;
	const confirm_password = document.getElementById("confirm-password").value;
	
	if(username == "" || user_email == "" || user_password=="" || confirm_password== ""){
		swal("OOPS!!!!!!", "Fill Up all the Columns", "warning");
		return;
	}

	console.log(username, user_email, user_password);
	
	if(user_password != confirm_password){
		swal("OOPS!!!!!!", "Passsword Mis-match", "error");
	}else{
		fetch("http://localhost:8086/signup" , {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				username: username,
				email: user_email,
				password: user_password
			})
		}).then(data => data).then(res => {
			if (res.status == 200) {
				swal("Great!", "Signed Up Successfully", "success");
				setTimeout(()=>{
					window.open('/', "_self");
				},500);
			} else {
				swal("OOPS!!!!!!", "Faild", "error")
			}
	
		})
		.catch(err => {
			swal("OOPS!!!!!!", "Account Exists", "warning")
		})
	}

}
 
function forget(event){
	event.preventDefault();
	const otp_mail = document.getElementById("email").value;
	console.log(otp_mail);

	if(otp_mail == ""){
		swal("OOPS!!!!!!", "Fill Up all the Columns", "warning");
		return;
	}
	fetch("http://localhost:8086/sendotp", {
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
		window.location.replace('/');
		return;
	}

	console.log(">>>>>>>>>>>", email);

	if(user_otp == ""){
		swal("OOPS!!!!!!", "Please Enter the OTP", "warning");
		return;
	}
	fetch("http://localhost:8086/checkotp", {
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
