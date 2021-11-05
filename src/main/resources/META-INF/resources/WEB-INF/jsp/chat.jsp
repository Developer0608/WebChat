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
	<script src="../../static/js/validate.js"></script>
	<script src="../../static/js/api.js"></script>
	<script src="../../static/js/app.js"></script>
	
</head>

<body>
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
			<input type="text" placeholder="Search contacts..." />
		</div>
		<div id="contacts">
			<ul id="contact-list">
				<li class="contact active" id="sanand404@gmail.com">
					<div class="wrap"> 
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Anand</p>
							<p class="preview">You just got LITT up, Mike.</p>
						</div>
					</div>
				</li>
				<li class="contact" id="azadsingh42878@gmail.com">
					<div class="wrap">
						 
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Azad</p>
							<p class="preview">Wrong. You take the gun, or you pull out a bigger one. Or, you call their bluff. Or, you do any one of a hundred and forty six other things.</p>
						</div>
					</div>
				</li>
				
			</ul>
		</div>
		<div id="bottom-bar">
			<button id="addcontact" onclick="AddtoContact(event)"><i class="fa fa-user-plus fa-fw" aria-hidden="true"></i> <span>Add contact</span></button>
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
				<li class="sent">
					<p>How the hell am I supposed to get a jury to believe you when I am not even sure that I do?!</p>
				</li>
				<li class="replies">
					<p>When you're backed against the wall, break the god damn thing down.</p>
				</li>
				<li class="replies">
					<p>Excuses don't win championships.</p>
				</li>
				<li class="sent">
					<p>Oh yeah, did Michael Jordan tell you that?</p>
				</li>
				<li class="replies">
					<p>No, I told him that.</p>
				</li>
				<li class="replies">
					<p>What are your choices when someone puts a gun to your head?</p>
				</li>
				<li class="sent">
					<p>What are you talking about? You do what they say or they shoot you.</p>
				</li>
				<li class="replies">
					<p>get out.</p>
				</li>
			</ul>
		</div>
		<div class="message-input">
			<div class="wrap">
			<input type="text" id="messageText" placeholder="Write your message..." />
			<i class="fa fa-paperclip attachment" aria-hidden="true">
				<!-- <input id="file" type="file" name="file" placeholder="Photo" required="" capture> -->
			</i>
			<button class="submit" onclick="sendMessage()" one><i class="fa fa-paper-plane" aria-hidden="true"></i></button>  
			</div>
		</div>

	</div>
</div>

</body>
<script src="../../static/js/chatapp.js"></script>
</html>