

function check_filed_del_usr()
{
    let username = document.getElementById("username_to_remove").value;

    if (username === '')
    {
        window.alert("All fields must be filled.")
        return 0; // invalid input.
    }
}

function check_filed_del_rest()
{
    let rest = document.getElementById("rest_to_remove").value;

    if (rest === '')
    {
        window.alert("All fields must be filled.")
        return 0; // invalid input.
    }
}
