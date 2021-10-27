function search(event){
    event.preventDefault();

    console.log('I am in search function');
    const email = document.getElementById('Email').value;

    if(email == ""){
        swal("OOPS!", "Specify Email", "warning");
        return;
    }
    console.log(email);
    fetch("http://localhost:8086/searchByMail", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            email: email
        }) 
    }).then(data => data).then(res => {
        if (res.status == 200) {
          swal("Great!", "Contact Saved", "success");
        } else {
          swal("OOPS!!!!!!", "Email is not Registered with us", "error")
        }
    
    }).catch(err => {
        swal("OOPS!!!!!!", "Email is not Registered with us", "error")
    })
}