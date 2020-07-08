const baseURL = "http://localhost:8080/";
// const baseURL = "../static";
init = function () {
    $.backstretch(baseURL + "/img/backgrounds/1.jpg");
    // $('#room_id-result-success').hide();
    // $('#room_id-result-danger').hide();
    // $('#result').hide();
}
init();
// add disable input on auto-generate id
$('#create-form-auto_generate').change(function toggleInputRoomId() {
    if (this.checked) {
        $('#create-form-room_id').prop('disabled', true);
        $('#room_id-result').prop('hidden', true);
        $('#create-form-room_id').val('****************************************************');
        $('#room_create_button').prop('disabled', false);
    } else {
        $('#create-form-room_id').prop('disabled', false);
        $('#room_id-result').prop('hidden', false);
        $('#room_id-result').text('');
        $('#create-form-room_id').val('');
        $('#room_create_button').prop('disabled', true);
    }
})
toggleAvailable = function (result) {
    var roomIdSuccess = $('#room_id-result-success');
    var roomIdFailure = $('#room_id-result-danger');
    if (result) {
        roomIdSuccess.prop('hidden',false);
        roomIdFailure.prop('hidden',true);
        $('#room_create_button').prop('disabled', false);
    } else {
        roomIdSuccess.prop('hidden',true);
        roomIdFailure.prop('hidden',false);
        $('#room_create_button').prop('disabled', true);
    }
}
// returns whether room id is available or not
verifyRoomId = function (roomId) {
    if (roomId == '') {
        return false;
    }

    var url = baseURL + "api/room/" + roomId;

    var method = "GET";
    var shouldBeAsync = true;

    var request = new XMLHttpRequest();

    request.onload = function () {

        var status = request.status; // HTTP response status, e.g., 200 for "200 OK"
        if(status == 302) {
             toggleAvailable(false);
        } else {
            toggleAvailable(true);
        }
    }

    request.open(method, url, shouldBeAsync);

    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    
    try {
        request.send();
    } catch (error) {
        console.log(error);
    }
}
// vertify room id on key-press
$('#create-form-room_id').on("keyup", function getInputData() {
    roomId = $(this).val();
    result = verifyRoomId(roomId);
    toggleAvailable(result);
})
//display result
displayResult = function (data, status) {
    if (status == 201) {
        var roomID = data.roomId;
        var password = data.password;
        $('#new_room_id').html(roomID);
        $('#new_room_password').html(password);
        $('#result').prop('hidden',false);
    }
}

// create room from backend api
$('.login-form').on("submit", function (e) {
    e.preventDefault();
    $('#result').prop('hidden',true);
    var roomId = $('#create-form-room_id').val();
    var roomPassword = $('#create-form-room_password').val();
    if (roomPassword == '') {
        return;
    }
    var auto_generate = $('#create-form-auto_generate').prop('checked');

    var url = baseURL + "api/room";

    var method = "POST";
    var postData = JSON.stringify({id: roomId, password: roomPassword, autoGenerate: auto_generate});
    var shouldBeAsync = true;

    var request = new XMLHttpRequest();

    request.onload = function () {

        var status = request.status; // HTTP response status, e.g., 200 for "200 OK"
        var data = JSON.parse(request.responseText);
        displayResult(data, status);
    }

    request.open(method, url, shouldBeAsync);

    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    
    try {
        request.send(postData);
    } catch (error) {
        console.log(error);
    }
})
