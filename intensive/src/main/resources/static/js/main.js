$(function(){

    let init = function() {
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
});

