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
	<script src="../../static/js/validate.js"></script>
	<script src="../../static/js/api.js"></script>
	 
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
			<ul>
				<li class="contact">
					<div class="wrap"> 
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Krishna</p>
							<p class="preview">You just got LITT up, Mike.</p>
						</div>
					</div>
				</li>
				<li class="contact active">
					<div class="wrap">
						 
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Rushabh</p>
							<p class="preview">Wrong. You take the gun, or you pull out a bigger one. Or, you call their bluff. Or, you do any one of a hundred and forty six other things.</p>
						</div>
					</div>
				</li>
				<li class="contact">
					<div class="wrap">
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Lorean</p>
							<p class="preview">I was thinking that we could have chicken tonight, sounds good?</p>
						</div>
					</div>
				</li>
				<li class="contact">
					<div class="wrap">
						 
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Ram</p>
							<p class="preview">Mike, I know everything! I'm Donna..</p>
						</div>
					</div>
				</li>
				<li class="contact">
					<div class="wrap">
						 
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Jessica Pearson</p>
							<p class="preview">Have you finished the draft on the Hinsenburg deal?</p>
						</div>
					</div>
				</li>
				<li class="contact">
					<div class="wrap">
 
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Rahul</p>
							<p class="preview">Thanks Mike! :)</p>
						</div>
					</div>
				</li>
				<li class="contact">
					<div class="wrap">
						 
						<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
						<div class="meta">
							<p class="name">Daniel Hardman</p>
							<p class="preview">We'll meet again, Mike. Tell Jessica I said 'Hi'.</p>
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
			<p>Azad</p>
			<div class="social-media">
				<i class='fas fa-video'></i>
                <i class='fas fa-phone-volume'></i>
			</div>
		</div>
		<div class="messages">
			<ul>
				<li class="sent">
					<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
					<p>How the hell am I supposed to get a jury to believe you when I am not even sure that I do?!</p>
				</li>
				<li class="replies">
					<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
					<p>When you're backed against the wall, break the god damn thing down.</p>
				</li>
				<li class="replies">
					<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
					<p>Excuses don't win championships.</p>
				</li>
				<li class="sent">
					<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
					<p>Oh yeah, did Michael Jordan tell you that?</p>
				</li>
				<li class="replies">
					<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
					<p>No, I told him that.</p>
				</li>
				<li class="replies">
					<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
					<p>What are your choices when someone puts a gun to your head?</p>
				</li>
				<li class="sent">
					<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
					<p>What are you talking about? You do what they say or they shoot you.</p>
				</li>
				<li class="replies">
					<img src="https://static.thenounproject.com/png/3321515-200.png" alt="" />
					<p>get out.</p>
				</li>
			</ul>
		</div>
		<div class="message-input">
			<div class="wrap">
			<input type="text" placeholder="Write your message..." />
             
			<i class="fa fa-paperclip attachment" aria-hidden="true">
				<!-- <input id="file" type="file" name="file" placeholder="Photo" required="" capture> -->
			</i>
			<button class="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>  
            
			</div>
		</div>

	</div>
</div>

</body>
<script src="../../static/js/chatapp.js"></script>
</html>