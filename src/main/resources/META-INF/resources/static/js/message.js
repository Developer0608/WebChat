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
        }
    }) 
    .catch(err => {
		console.log('Some error occured', err);
	})
}