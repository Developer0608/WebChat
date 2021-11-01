const token = localStorage.getItem("token")
console.log(token);
if(token === null || token == undefined == token == ""){
    window.open("/authenticate", "_self");
}