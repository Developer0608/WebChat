function searchContacts(event){
    event.preventDefault();

    const word = document.getElementById("search-text").value;
    console.log(word);

    console.log('I am in search Contacts');
    const domain = getDomain();

    console.log(domain);

    if(word.trim().length > 0) {
        fetch(`${domain}/search-users/${word}`, {
            method: "GET",
            headers: {
                "Content-type": "application/json",
                "Authorization": localStorage.getItem("token")
            },
        }).then(data => {
            if(data.status == 200){
                return data.json();
            }else{
                console.log('I am in else section')
            }
        })
        .then(res => {
            if(res){
                console.log('Displaying Response of Search Contacts')
                console.log(res);
                $('#search ul li').remove();
                for (const obj of res) {
                    console.log(obj.username, obj.email);
                    $('<li class="users-name" onclick=\"(activeContact(\'' + obj.username+ '\',\'' + obj.email + '\'))"\><p>'+ obj.email +'</p></li>').appendTo('#search ul')
                }
                
            }
        })
        .catch(err => {
            console.log('some error occured');
            $('.messages ul li').remove();
        })
    } else {
        $('#search ul li').remove();
    }

}