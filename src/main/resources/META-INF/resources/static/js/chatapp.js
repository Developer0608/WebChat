document.getElementById("loggeduser").innerHTML = localStorage.getItem("username");

$("#profile-img").click(function() {
	$("#status-options").toggleClass("active");
});

$(".expand-button").click(function() {
  $("#profile").toggleClass("expanded");
	$("#contacts").toggleClass("expanded");
});


// For contact select
$("#contact-list").on('click', 'li', function() {
  $("#contact-list li.active").removeClass('active');
  $(this).addClass('active');
  activeContactUserName = $("#contact-list li.active p.name").text();
  $(".content p.username").text(activeContactUserName);

});

$("#status-options ul li").click(function() {
	$("#profile-img").removeClass();
	$("#status-online").removeClass("active");
	$("#status-away").removeClass("active");
	$("#status-busy").removeClass("active");
	$("#status-offline").removeClass("active");
	$(this).addClass("active");
	
	if($("#status-online").hasClass("active")) {
		$("#profile-img").addClass("online");
	} else if ($("#status-away").hasClass("active")) {
		$("#profile-img").addClass("away");
	} else if ($("#status-busy").hasClass("active")) {
		$("#profile-img").addClass("busy");
	} else if ($("#status-offline").hasClass("active")) {
		$("#profile-img").addClass("offline");
	} else {
		$("#profile-img").removeClass();
	};
	
	$("#status-options").removeClass("active");
});

function newMessage() {
	message = $(".message-input input").val();
	if($.trim(message) == '') {
		return false;
	}
	$('<li class="sent"><p>' + message + '</p></li>').appendTo($('.messages ul'));  
	$('.message-input input').val(null);
	$('.contact.active .preview').html('<span>You: </span>' + message);
	$(".messages").animate({ scrollTop: $(document).height() }, "fast");
};

$('.submit').click(function() {
  newMessage();
});

 z
$(window).on('keydown', function(e) {
  if (e.which == 13) {
    sendMessage();
    newMessage();
    return false;
  }
});

$("#profileImage").click(function(e) {
  $("#imageUpload").click();
});

function fasterPreview( uploader ) {
  if ( uploader.files && uploader.files[0] ){
        $('#profileImage').attr('src', 
           window.URL.createObjectURL(uploader.files[0]) );
  }
}

$("#imageUpload").change(function(){
  fasterPreview( this );
});



//function to move back from setting page to chat page
function back(event){
  event.preventDefault();

  console.log('I am in back function');

  window.open('/', '_self');
}

//function to update username
function updateUsername(event){
  event.preventDefault();
  
  console.log('I am in update username')
  const username = document.getElementById('username').value;
  
  const email = localStorage.getItem("email");

  if(username == ""){
    swal("OOPS!!!!!!", "Fill Up Username", "warning");
    return;
  }

  fetch(`${domain}/users/${email}` , {
    method: "PUT",
		headers: {
			"Content-Type": "application/json",
      "Authorization": localStorage.getItem("token")
		},
		body: JSON.stringify({
      username: username,
			email: email, 
		})
	}).then(data => data.json()).then(res => {
      localStorage.setItem("username", res.username);
      // document.getElementById("loggeduser").innerHTML = localStorage.getItem("username");
			swal("Great!", "Updated Successfully", "success");
			setTimeout(()=>{
				window.open('setting', "_self");
			},1500)

	})
	.catch(err => {
		swal("OOPS!!!!!!", "Some Problem Occurred", "error");
  })

}


//cancelling event function
function cancel(event){
  event.preventDefault();

  swal("Done", "Cancelled", "success");
  setTimeout(()=>{
    window.open("/setting", "_self");
  },1500)
}


//function to call setting page from chat page
function setting(event){
	event.preventDefault();

  console.log('>>>>I am in setting function>>>>')
  const email = localStorage.getItem('loggedEmail');
  console.log(email);
    
  window.open("/setting", "_self");

}


//function to logout the loggedin user
function logout(event){
  event.preventDefault();

  localStorage.clear();
  console.log('I am in Logout Function');
  swal({
    position: 'top-end',
    icon: 'success',
    title: 'Logged Out Successfully',
    showConfirmButton: false,
  })
  
  setTimeout(()=>{
    window.open("/authenticate", "_self");
  },1500)
}

function AddtoContact(event){
  event.preventDefault();

  window.open("/contact", "_self");
}

