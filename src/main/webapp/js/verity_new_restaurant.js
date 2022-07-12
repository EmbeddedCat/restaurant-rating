function verify_add_rest()
{
    let name    = document.getElementById("restaurant_name").value;
    let address = document.getElementById("restaurant_address").value;
    let phone   = document.getElementById("restaurant_address").value;
    let pic     = document.getElementById("restaurant_pic").value;

    if (name === '' || address === ''
        || phone === '' || pic === '')
    {
        window.alert("All fields must be filled.")
        return 0; // invalid input.
    }

    return 1; // valid input.
}