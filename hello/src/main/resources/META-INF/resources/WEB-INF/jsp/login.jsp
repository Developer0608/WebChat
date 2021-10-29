<!-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> -->
<!Doctype html>
<html>

<head>
    <meta charset="UTF-8" />
    <title>WEBCHAT</title>
    <link href="../../static/css/signup.css" type="text/css" rel="stylesheet" />
	<link rel="icon" href="../../static/pics/logo.jpg" type="image/x-icon">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="sweetalert2.all.min.js"></script>
    <!-- <link rel="icon" href="../../static/pics/logo.jpg" type="image/x-icon"> -->
</head>

<body>
    <div class="container" id="container">
        <div id="loading" class="center"></div>
        <div class="form-container sign-up-container">
            <form action="">
                <h1>Create Account </h1>
                <div class="social-container">
                    <a href="https://accounts.google.com/signin/v2/identifier?hl=en&continue=https%3A%2F%2Fmail.google.com%2Fmail&service=mail&ec=GAlAFw&flowName=GlifWebSignIn&flowEntry=AddSession" class="social" target="_blank"><i class="fab fa-google-plus-g"></i></a>
                </div>
                <input type="text" placeholder="Name" id="name" required="required" />
                <input type="email" placeholder="Email" id="email" required="required" />
                <input type="password" placeholder="Password" id="password" required="required" />
                <input type="password" placeholder="Confirm Password" id="confirm-password" required="required" />
                <button type="submit" onclick="signup(event)">Sign Up</button>
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form>
                <h1>Sign in</h1>
                <div class="social-container">
                    <a href="https://accounts.google.com/signin/v2/identifier?hl=en&continue=https%3A%2F%2Fmail.google.com%2Fmail&service=mail&ec=GAlAFw&flowName=GlifWebSignIn&flowEntry=AddSession" class="social" target="_blank" rel="noopener noreferrer"><i class="fab fa-google-plus-g"></i></a>
                </div>
                <input type="email" placeholder="Email" id="login_email" name="email" required="" />
                <input type="password" placeholder="Password" id="login_password" name="password" required="" />
                <a href="forget">Forgot your password?</a>
                <button onclick="login(event)">Sign In</button>
                <br>
            </form>
            <button href = "login">ADMIN LOGIN</button>

        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h2>WELCOME TO WEBCHAT</h2>
                    <div class="wrapper">
                        <img src="../../static/pics/logo-removebg-preview.png" alt="Trulli" width="100" height="100">
                    </div>
                    <br><br><br><br><br><br><br><br><br><br>
                    <button class="ghost" id="signIn">Sign In</button>

                </div>
                <div class="overlay-panel overlay-right">
                    <h2>WEBCHAT</h2>
                    <div class="wrapper1">
                        <img src="../../static/pics/logo-removebg-preview.png" alt="Trulli" width="100" height="100">
                    </div>
                    <br><br><br><br><br><br><br><br><br><br>
                    <button class="ghost" id="signUp">Sign Up</button>
                </div>
            </div>
        </div>
    </div>

    <footer>
        <p>
            SAVE WATER, SAVE LIFE
        </p>
    </footer>
</body>
<script src="../../static/js/login.js"></script>

</html>