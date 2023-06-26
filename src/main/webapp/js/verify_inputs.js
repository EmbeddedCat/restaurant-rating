function verify_register()
{
    let username   = document.getElementById("register_username").value;
    let password   = document.getElementById("register_password").value;
    let password_v = document.getElementById("register_password_v").value;
    let email      = document.getElementById("register_email").value;

    // check if anything is empty.
    if (username === '' || password === '' || password_v === ''
        || email === '')
    {
        window.alert("All fields must be filled.");
        return 0; // invalid input.
    }
    if (password !== password_v)
    {
        window.alert("Password must be the same.");
        return 0; // invalid input.
    }

    return 1; // valid input.
}

function verify_login()
{
    let username = document.getElementById("login_username").value;
    let password = document.getElementById("login_password").value;
    if (username === '' || password === '')
    {
        window.alert("All fields must be filled.");
        return 0; // invalid input.
    }

    return 1; // valid input.
}

function verify_auth()
{
    let auth = document.getElementById("auth_code").value;
    if (auth === '') {
        window.alert("All fields must be filled.");
        return 0;
    }
    return 1;
}

function verify_passwd_recov() 
{
    let email = document.getElementById("pass_recov").value;
    if (email === '') {
        window.alert("All fields must be filled.");
        return 0;
    } 
    return 1;
}

function verify_change_passwd() {
    let new_password = document.getElementById("new_password").value;
    let new_password_again = document.getElementById("new_password_again").value;
    if (new_password === '' || new_password_again === '')
    {
        window.alert("All fields must be filled.");
        return 0; // invalid input.
    }

    return 1; // valid input.

}