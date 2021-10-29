// $(".messages").animate({ scrollTop: $(document).height() }, "fast");

user = localStorage.getItem("loggedUser");
userObj = JSON.parse(user);
console.log(userObj);

document.getElementById("loggeduser").innerHTML = userObj.username;

$("#profile-img").click(function() {
	$("#status-options").toggleClass("active");
});

$(".expand-button").click(function() {
  $("#profile").toggleClass("expanded");
	$("#contacts").toggleClass("expanded");
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
	$('<li class="sent"><img src="http://emilcarlsson.se/assets/mikeross.png" alt="" /><p>' + message + '</p></li>').appendTo($('.messages ul'));
	$('.message-input input').val(null);
	$('.contact.active .preview').html('<span>You: </span>' + message);
	$(".messages").animate({ scrollTop: $(document).height() }, "fast");
};

$('.submit').click(function() {
  newMessage();
});

$(window).on('keydown', function(e) {
  if (e.which == 13) {
    newMessage();
    return false;
  }
});


function back(event){
  event.preventDefault();

  console.log('I am in back function');

  window.open('/chat', '_self');
}
function account(event){
  event.preventDefault();
  
  var email = userObj.email;
  // console.log(email);

  var username = document.getElementById('username').value;
  // const email = document.getElementById('email').value;
  
  console.log(email, username);

  console.log('>>>>> ACCOUNT >>>>>');

  if(username == ""){
    swal("OOPS!!!!!!", "Fill Up Username", "warning");
    return;
  }

  fetch("http://localhost:8086/account" , {
    method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
      username: username,
			email: email, 
		})
	}).then(data => data).then(res => {
		if (res.status == 200) {
			swal("Great!", "Updated Successfully", "success");
			setTimeout(()=>{
				window.open('setting', "_self");
			},1500)
		} else {
			swal("OOPS!!!!!!", "Some Problem Occurred", "error");
		}

	})
	.catch(err => {
		swal("OOPS!!!!!!", "Some Problem Occurred", "error");
  })

}

function cancel(event){
  event.preventDefault();

  swal("Done", "Cancelled", "success");
  setTimeout(()=>{
    window.open("/setting", "_self");
  },1500)
}

function setting(event){
	event.preventDefault();

  console.log('>>>>I am in setting function>>>>')
  const email = localStorage.getItem('loggedEmail');
  console.log(email);
    
  window.open("/setting", "_self");

}

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
    window.open("/", "_self");
  },1500)
}

function settingdata(event){
  event.preventDefault();

  console.log('Modifying Data via setting');

  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  console.log(username, password);

  if(username == "" || password == ""){
    swal("OOPS!!!!!!", "Fill Up all the Columns", "warning");
    return;
  }

  fetch("http://localhost:8086/profileupdated", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      username: username,
      password: password
    })
  }).then(data => data).then(res => {
    if (res.status == 200) {
      swal("Great!", "Username Updated Successfully", "success");
    } else {
      swal("OOPS!!!!!!", "Faild", "error")
    }

  })
  .catch(err => {
    swal("OOPS!!!!!!", "Account Exists", "warning")
  })
}

function AddtoContact(event){
  event.preventDefault();

  window.open("/contact", "_self");
}
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