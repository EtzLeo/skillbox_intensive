$(function(){

    var username = 'currentUser';

    let init = function() {
        loadUsers();
        loadMessages();
    };

    let authorise = function() {
        let name = prompt('Input username:');
        username = name;
        $.post('/api/users', {'name' : name}, function(response){
            if (response.result) {
                init();
            } else {
                alert('Something gone wrong...');
            }
        });
    };

    let checkAuthStatus = function() {
        $.get('/api/auth', function(response){
            if (response.result) {
                username = response.name;
                init();
            } else {
                authorise();
            }
        });
    };

    checkAuthStatus();

    let loadUsers = function() {
        $.get('/api/users', function(response){
            let users = response.users;
            let usersList = $('.users-list');
            for(let i in users){
                let userItem = $('<div class="user-item"></div>');
                userItem.text(users[i].name);
                usersList.append(userItem);
            }
        });
    };

    let loadMessages = function() {
        let messagesList = $('.messages-list');
                $.get('/api/messages', function(response) {
                    let messages = response.messages;
                    for(let i in messages) {
                        let messageItem = $('<div class="message"><b>' +
                            messages[i].time + "&nbsp;" + "[" +
                            messages[i].name + "]" +
                            '</b> ' + messages[i].text + '</div>');
                        messagesList.append(messageItem);
                    }
                });
    };

    $('.send-message').on('click', function(){
        let message = $('.message-text').val();
        let messagesList = $('.messages-list');
        $.post('/api/messages', {'text': message}, function(response){
            if(response.result) {
                let messageItem =
                    $('<div class="message"><b>'
                        + response.time
                        + "&nbsp;" + "["
                        + username + "]"
                        + '</b> '
                        + message
                        + '</div>');
                messagesList.append(messageItem);
                $('.message-text').val('');
            } else {
                alert('Something gone wrong...');
            }
        });
    });
});

