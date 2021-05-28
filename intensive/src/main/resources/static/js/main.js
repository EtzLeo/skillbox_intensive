$(function(){

    let init = function() {
        loadUsers();
        //TODO load users, load messages
        alert('OK');
    };

    let authorise = function() {
        let name = prompt('Input username:');
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
});

