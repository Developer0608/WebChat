const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
const domain = getDomain();

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

//function to trigger when user is doing login
function login(event) {
	event.preventDefault();

	const email = document.getElementById("login_email").value;
	const password = document.getElementById("login_password").value;

	console.log(email, password);

	if(email == "" || password == ""){
		swal("OOPS!!!!!!", "Fill Up all the Columns", "warning");
		return;
	}

	fetch(`${domain}/authenticate`, {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			email: email, 
			password: password, 
			})
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
			
			localStorage.setItem('email', (res.email));
			localStorage.setItem('username', (res.username));
			localStorage.setItem('token', `Bearer ${res.jwttoken}`)
			console.log(res);
			setTimeout(()=>{
				console.log('I am in timeout function');
				console.log("Passed EMAIL ::: ", res.email)
				getMessage(localStorage.getItem('email'));
				window.open('/', "_self");
			},500);
		}
	})
	.catch(err => {
		swal("OOPS!!!!!!", "Account Does't Exist", "error");
	})
}


//function to trigger when user is doing signup.
function signup(event){
	event.preventDefault();

	const username = document.getElementById("username").value;
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
		fetch(`${domain}/register` , {
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
				swal("OOPS!!!!!!", "Account Already Exists", "error")
			}
	
		})
		.catch(err => {
			swal("OOPS!!!!!!", "Account Exists", "warning")
		})
	}

}
