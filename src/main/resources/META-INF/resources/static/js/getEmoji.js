function getEmoji(){
    console.log('I am in emoji');

    tinymce.init({
        selector: ".message-input",
        plugins: "emoticons autoresize",
        toolbar: "emoticons",
        toolbar_location: "top",
        menubar: false,
        statusbar: false
      });   
}
