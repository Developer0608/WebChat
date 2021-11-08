console.log('I am in contactList ');


function getContact(email) {

    console.log('I am in contact function');
    console.log('EMAIL' , email);

    const domain = getDomain();

    console.log(domain);

    fetch(`${domain}/contact-list/${email}`, {
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
            console.log('Displaying Response');
            for (const obj of res) {
                $('<li class="contact" id="' + obj.email + '" onclick=\"(getActiveMessage(\'' + obj.email  + '\'))"\><div class="wrap"><div class="meta"><p class="name">'+ obj.username +'</p></li>').appendTo($('#contacts ul')); 
            }
            console.log(res);
        }
    }) 
    .catch(err => {
		console.log('Some error occured', err);
	})
}
