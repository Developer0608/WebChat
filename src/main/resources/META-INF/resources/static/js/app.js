
let stompClient;

function test() {
    console.log('############');
}

console.log('>>>>>>>>>>>');
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

function connectClient() {
    console.log('>>>>>>>>>>..');

	setConnected(true);

    const sockJs = new SockJS("/chat");
    console.log('Connecting......');
    console.log(sockJs);
    stompClient = Stomp.over(sockJs);
    console.log(stompClient);

    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected');
    });
}

function sendClient() {
    console.log('Sending Message ', stompClient);
    stompClient.send("/app/chat", {}, JSON.stringify({"From": "Azad", "to": "Hi To Anand"}));
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}