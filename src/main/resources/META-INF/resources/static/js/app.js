let stompClient;


const sockJs = new SockJS("/chat");
console.log('Connecting......');
console.log(sockJs);
stompClient = Stomp.over(sockJs);
console.log(stompClient);

stompClient.connect({}, function(frame) {
    setConnected(true);
    console.log('Connected');
    stompClient.subscribe(`/topic/messages/${localStorage.getItem("email")}`, function(serverMessage) {
        console.log("#####################");
        console.log(JSON.parse(serverMessage.body));

        const receivedMessage = JSON.parse(serverMessage.body);
        
        $('<li class="replies"><p>' + receivedMessage + '</p></li>').appendTo($('.messages ul'));  
        $('.contact.active .preview').html('<span>You: </span>' + receivedMessage);
        $(".messages").animate({ scrollTop: $(document).height()-100}, 1000);
    })
});

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function sendMessage() {

    const activeUser = $("#contact-list li.active").attr("id");
    console.log(activeUser);

    message = $(".message-input input").val();
    console.log('Sending Message ', message, stompClient);
    stompClient.send("/app/chat", {}, JSON.stringify({"from": localStorage.getItem("email"), "to": activeUser, "message": message}));
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}