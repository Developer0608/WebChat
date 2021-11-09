<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>WEBCHAT</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <link rel="icon" href="../../static/pics/logo.jpg" type="image/x-icon">
    <link rel="stylesheet" href="../../static/css/chatapp.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
	<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
	<script src="../../static/js/validate.js"></script>
	<script src="../../static/js/api.js"></script>
	<script src="../../static/js/app.js"></script>
	<script src="../../static/js/message.js"></script>
	<script src="../../static/js/searchContact.js"></script>
	<script src="../../static/js/activeContact.js"></script>
	<script src="../../static/js/getEmoji.js"></script>
</head>

<body onload="getContact(localStorage.getItem('email'))">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<div id="frame">
	<div id="sidepanel">
		<div id="profile">
			<div class="wrap">
				<img id="profile-img" src="https://static.thenounproject.com/png/3321515-200.png" class="online" alt="" />
				<p id="loggeduser">Username</p>
			</div>
		</div>
		<div id="search">
			<label for=""><i class="fa fa-search" aria-hidden="true"></i></label>
			<input type="text" id="search-text" placeholder="Search contacts..." onkeyup="searchContacts(event)" />
			<ul class="searched-user">
			</ul>
		</div>
		<div id="contacts">
			<ul id="contact-list">
			</ul>
		</div>
		<div id="bottom-bar">
			<button id="settings" onclick="setting(event)"><i class="fa fa-cog fa-fw" aria-hidden="true"></i> <span>Settings</span></button>
		</div>
	</div>
	<div class="content">
		<div class="contact-profile">
			<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
			<p class="username">Azad</p>
			<div class="social-media">
				<i class='fas fa-video'></i>
                <i class='fas fa-phone-volume'></i>
			</div>
		</div>
		<div class="messages">
			<ul>
			</ul>
		</div>
		<div class="message-input">
			<div class="wrap">
			<i id="emoji" class='far fa-grin' onclick = "getEmoji()" area-hidden="true" style="position: relative; margin-top: 10px;"></i>
			<input type="text" id="messageText" placeholder="Write your message..." />
			<button class="submit" onclick="sendMessage()" one><i class="fa fa-paper-plane" aria-hidden="true"></i></button>  
			</div>
		</div>

	</div>
</div>

</body>
<script src="../../static/js/chatapp.js"></script>
<script src="../../static/js/contactlist.js"></script>
</html>