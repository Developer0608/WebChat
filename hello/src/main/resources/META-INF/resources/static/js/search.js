function search(event){
    event.preventDefault();

    console.log('I am in search function');
    const email = document.getElementById('Email').value;

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
          swal("Great!", "Account Exist with us", "success");
        } else {
          swal("OOPS!!!!!!", "Email is not Registered with us", "error")
        }
    
    }).catch(err => {
        swal("OOPS!!!!!!", "Email is not Registered with us", "error")
    })
}