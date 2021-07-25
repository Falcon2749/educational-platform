let form = document.getElementById("new_user");

form.onsubmit = function (event) {
    event.preventDefault();
    let formData = JSON.stringify({
        "name": document.getElementById("firstName").value,
        "surname": document.getElementById("lastName").value,
        "email": document.getElementById("email").value,
        "password": document.getElementById("password").value,
    });
    postUserData(formData);
    console.log(formData);
}

async function postUserData(formData){
    try {
        const response = await fetch('/registration', {
            method: 'POST',
            body: formData,
            headers: {
                'Content-Type': 'application/json'
            }
        })
        response.text().then(function (text) {
            console.log(text)
            var messageBlock = document.getElementById("messageBlock");
            messageBlock.innerHTML = text;
            console.log(text == "Успішно зареєстровано");
            if (text == "Успішно зареєстровано"){
                messageBlock.classList.remove("alert-danger");
                messageBlock.classList.add("alert-success");
            } else {
                messageBlock.classList.add("alert-danger");
            }
            messageBlock.classList.remove("hidden");
        });


    } catch (error) {
        console.error('Error:', error);
    }
}