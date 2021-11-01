const token = localStorage.getItem("token")
console.log(token);
if(token === null){
    window.open("/authenticate", "_self");
}