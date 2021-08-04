window.onload = function () {
    getUserInfo();
}

async function getUserInfo() {
    try {
        let user = JSON.parse(localStorage.getItem("user"));
        if (user != null){
            setProfileInfo(user);
            return;
        }

        await fetch(URL + "/learn/profile.json", {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then((response) => {
                console.log(JSON.stringify(response))
                localStorage.setItem('user', JSON.stringify(response));
                setProfileInfo(response);
            });
    } catch (error) {
        console.error('Помилка:', error);
    }
}

function setProfileInfo(userObject){
    document.querySelector("#name").innerHTML = userObject.name;
    document.querySelector("#ecoin").innerHTML = "Ecoin: " + userObject.ecoin;
    document.querySelector("#xp").innerHTML = "XP: " + userObject.xp;
}

function logOut() {
    localStorage.removeItem("user");
    window.location.href = "index.html";
}