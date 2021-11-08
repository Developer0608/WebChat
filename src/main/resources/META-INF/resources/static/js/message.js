function getActiveMessage(email) {

    console.log('I am in message function' , email);
    console.log('EMAIL' , email);

    const domain = getDomain();

    console.log(domain);

    fetch(`${domain}/messages/${email}`, {
        method: "GET",
        headers: {
            "Content-type" : "application/json",
            "Authorization": localStorage.getItem("token")
        },
    }).then(data => {
        if(data.status == 200){
            console.log('I am in if section')
            return data.json();
        }else{
            console.log('I am in else section');
        }
    })
    .then(res => {
        if(res){
            console.log('I am about to print response');
            console.log(res);
            
            $('.messages ul li').remove();
            for (const obj of res) {
                if(obj.sender == localStorage.getItem('email')){
                    $('<li class="sent"><p>' + obj.messages + '</p></li>').appendTo($('.messages ul'));  
                    $('.message-input input').val(null);
                    $('.contact.active .preview').html('<span>You: </span>' + obj.messages);
                    // $(".messages").animate({ scrollTop: $(document).height() }, "fast");
                }
            
                if(obj.receiver == localStorage.getItem('email')){
                    $('<li class="replies"><p>' + obj.messages + '</p></li>').appendTo($('.messages ul'));  
                    $('.message-input input').val(null);
                    $('.contact.active .preview').html('<span>You: </span>' + obj.messages);
                }
            }
            $(".messages").animate({ scrollTop: $(document).height()+$(window).height() }, "fast");
        }
    }) 
    .catch(err => {
		console.log('Some error occured', err);
	})
}