
function change_email() {
    new_email = prompt("New email", "Your email");
    is_valid = new_email.toLowerCase()
                        .match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    if (new_email == null || !is_valid) {
        window.alert("Wrong email addreess");
    } else {
        document.getElementById("change_email_td").innerHTML = new_email;
        document.getElementById("change_email").value = new_email;
        window.alert("Please click on 'save' button to save the changes");
    }
}

function change_address() {
    new_address = prompt("New address", "Your address");
    if (new_email == null) {
        window.alert("You must fill the input field");
    } else {
        document.getElementById("change_addr_td").innerHTML = new_address;
        document.getElementById("change_addr").value = new_address;
        window.alert("Please click on 'save' button to save the changes");
    }
}