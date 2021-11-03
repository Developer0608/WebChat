<!DOCTYPE html>
<html>

<head>
    <title>WEBCHAT</title>
    <link href="../../static/css/contact.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <link rel="icon" href="../../static/pics/logo.jpg" type="image/x-icon">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="../../static/js/validate.js"></script>
     
</head>

<body>
     
    <div class="container">  
        <div class="contact-box">
            <div class="left">
                <img id = "image" src="../../static/pics/logo-removebg-preview.png" alt="Trulli" width="100" height="100">
            </div>

            <div class="right">
                <h2>New contact</h2>
                <br>
                <input type="text" class="field" placeholder="Email" id="Email">
                <button class="btn" onclick="search(event)">Save</button>
            </div>
        </div>
    </div>
</body>
<script src="../../static/js/search.js"></script>
</html>