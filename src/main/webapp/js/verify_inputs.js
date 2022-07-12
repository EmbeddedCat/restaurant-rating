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
        window.alert("All fields must be filled.")
        return 0; // invalid input.
    }
    if (password !== password_v)
    {
        window.alert("Password must be the same.")
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
        window.alert("All fields must be filled.")
        return 0; // invalid input.
    }

    return 1; // valid input.
}