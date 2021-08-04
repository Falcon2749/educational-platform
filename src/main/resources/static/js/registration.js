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
}

async function postUserData(formData) {
    try {
        await fetch(URL + '/registration', {
            method: 'POST',
            body: formData,
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                console.log(response.status);
                var messageBlock = document.getElementById("messageBlock");

                if (response.status === 201) {
                    messageBlock.classList.remove("alert-danger");
                    messageBlock.classList.add("alert-success");
                    messageBlock.innerHTML = "Успішно зареєстровано. <a href='" + LOGIN_URL + "'>Авторизуватися</a>"
                } else if (response.status === 409) {
                    messageBlock.classList.remove("alert-success");
                    messageBlock.classList.add("alert-danger");
                    messageBlock.innerHTML = "Користувач з даним email вже існує";
                } else {
                    messageBlock.classList.remove("alert-success");
                    messageBlock.classList.add("alert-danger");
                    messageBlock.innerHTML = "Помилка реєстрації. Error Code - " + response.status;
                }
                messageBlock.classList.remove("hidden");
            });


    } catch (error) {
        console.error('Error:', error);
    }
}