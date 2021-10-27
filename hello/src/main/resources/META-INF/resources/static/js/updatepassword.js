function updatePassword(event){
    event.preventDefault();
  
    const email = localStorage.getItem('loggedEmail');
    const oldpassword = document.getElementById('oldpassword').value;
    const newpassword = document.getElementById('newpassword').value;
    const confirmpassword = document.getElementById('confirmpassword').value;
  
  
    console.log(email, oldpassword, newpassword, confirmpassword);
  
    if(oldpassword == newpassword){
      swal("OOPS!!!!!!", "New Password Cannot be same as Old Password", "warning");
      return;
    }
  
    if(oldpassword == "" || newpassword == "" || confirmpassword == ""){
      swal("OOPS!!!!!!", "Fill Up All Columns", "warning");
      return;
    }
  
    if(newpassword == confirmpassword){
        fetch("http://localhost:8086/checkpassword", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            password: oldpassword,
            email: email
          })		
        }).then(data => data).then(res => {
            if (res.status == 200) {
               fetch("http://localhost:8086/updatepassword", {
                  method: "POST",
                  headers: {
                    "Content-Type": "application/json"
                  },
                  body: JSON.stringify({
                    password: newpassword,
                    email: email
                  })	
               }).then(data => data).then(res => {
                if (res.status == 200) {
                  swal("Great!", "Password Updated Successfully", "success");
                } else {
                  swal("OOPS!!!!!!", "Old Password is Incorrect", "error")
                }
            
              })
              .catch(err => {
                swal("OOPS!!!!!!", "Some Problem Occured", "warning")
              })
            } else {
              swal("OOPS!!!!!!", "Old Password is Incorrect", "error")
            }
        
          })
          .catch(err => {
            swal("OOPS!!!!!!", "Account Exists", "warning")
          })
    }else{
      swal("OOPS!!!!!!", "Password Mis-match", "warning");
      return;
    }
  }