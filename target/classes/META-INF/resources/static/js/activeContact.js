function activeContact(username, email){
    console.log('I am in active contact');

    $('#search ul li').remove();
    console.log('OBJECT = ',username,  email);
    displayContact(username, email);
}