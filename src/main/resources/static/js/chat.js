var username = $('#username').text();
var roomID = $('#room_id').text();
var participants = $('.contacts');
const baseURL = "https://insta-chat-app.herokuapp.com/";
var socket = null;
var stomp = null;
var onlineUsers = [];
var currentUser = null;
getOnlineUsers = function (roomId) {
    var url = baseURL + "api/room/" + roomId + "/user";
    var method = "GET";
    var shouldBeAsync = false;

    var request = new XMLHttpRequest();
    request.onload = function () {
        var status = request.status;
        if (status == 302) {
            var body = request.response;
            var users = JSON.parse(body);
            onlineUsers = users;
        }
    }
    request.open(method, url, shouldBeAsync);
    // request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    try {
        request.send();
    } catch (error) {
        console.log(error);
    }
}

setUser = function () {
    var url = baseURL + "api/user/" + username;
    var method = "GET";
    var shouldBeAsync = false;

    var request = new XMLHttpRequest();
    request.onload = function () {
        var status = request.status;
        if (status == 200) {
            var body = request.response;
            currentUser = JSON.parse(body);
        }
    }
    request.open(method, url, shouldBeAsync);
    // request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    try {
        request.send();
    } catch (error) {
        console.log(error);
    }
}
getUser = function () {

    if(currentUser == null) {
        setUser();
        $('#current_user').attr("id",currentUser.sessionId);
    }
    return currentUser;
}
onNewMessage = function (response) {
    var body = response.body;
    message = JSON.parse(body);
    displayMessage(message);
}

displayMessage = function (message) {
    console.log(message.from);
    console.log(getUser());
    if(message.from.sessionId != getUser().sessionId) {
        console.log("message");
        
        var template = getNewReceiveMessageTemplate(message.from.username,message.content);
        $('#messages').append(template);
    }
}

onNewEvent = function (response) {
    var body = response.body;
    event = JSON.parse(body);
    handleEvent(event);

}

handleEvent = function (event) {
    user = event.user;
    switch (event.type) {
        case "JOIN":
            participants.append(getNewUserTemplate(user.username,user.sessionId));
            break;
        case "LEAVE":
            sessionId =user.sessionId;
            $('#'+sessionId).remove();
            break;
        case "TYPING":
            $('#'+user.sessionId).find("p").prop("hidden",false);
            setTimeout(function() {
                $('#'+user.sessionId).find("p").prop("hidden",true);
            },750);
            break;
        default:
            break;
    }
}

onConnected = function (frame) {
    stomp.subscribe("/messages/" + roomID, onNewMessage, {username: username});
    stomp.subscribe("/events/" + roomID, onNewEvent);
}
getNewReceiveMessageTemplate = function (username, content) {
    var template = `
    <div class="d-flex justify-content-start mb-4">
        <div class="msg_cotainer">
            ${content}
            <div class="msg_time">${username}</div>
        </div>
    </div>
    `;
    return template;
}
getNewSendMessageTemplate = function (content) {
    var template = `
    <div class="d-flex justify-content-end mb-4">
        <div class="msg_cotainer_send">
            ${content}
            <span class="msg_time_send">You</span>
        </div>
    </div>
    `;
    return template;
}
getNewUserTemplate = function (username,id) {
    var template = `
    <li id ="${id}" class="">
        <div class="d-flex bd-highlight text-center">
            <div class="user_info">
                <span>${username}</span>
                <p hidden="true">Typing</p>
            </div>
        </div>
    </li>
    `;
    return template;
}
prepareRequestPayload = function(user,message) {

    var payload = JSON.stringify({from:user,content:message});
    return payload;
}
//send message
sendMessage = function() {
    var message = $("#message_box").val();
    $('#message_box').val("");
    if(message.trim() == '') {
        alert("please type something");
        return;
    }
    user = getUser();
    console.log(user);
    payload = prepareRequestPayload(user,message);
    var template = getNewSendMessageTemplate(message);
    stomp.send("/message/"+roomID,{},payload);
    $('#messages').append(template);
}

var url = baseURL + "chat";
socket = new SockJS(url);
getOnlineUsers(roomID);
onlineUsers.forEach(function (user, index) {
    participants.append(getNewUserTemplate(user.username,user.sessionId));
})
stomp = Stomp.over(socket);
stomp.connect({}, onConnected);
//send event
$('#send_button').on("click",sendMessage);
$("#message_box").keypress(function (e) {
    if(e.which == 13 && !e.shiftKey) {
        e.preventDefault();
        sendMessage();
        $("#message_box").val('');
    }
});

$('#message_box').on("keydown",function(){
    sendEvent("TYPING")
});

sendEvent = function(event) {
    user = getUser();
    payload = JSON.stringify({type:event,user:user});
    stomp.send("/events/"+roomID,{},payload);
}