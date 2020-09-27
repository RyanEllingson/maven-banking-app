const app = function() {

    const usernameEl = document.getElementById("username-el");
    const passwordEl = document.getElementById("password-el");
    const loginBtnEl = document.getElementById("login-btn");

    const handleSubmit = async function(event) {
        event.preventDefault();
        const username = usernameEl.value;
        const password = passwordEl.value;
        const response = await axios.post("/rocp-project/login",
        {
            username: username,
            password: password
        });
        if (response.data.message) {
            console.log(response.data.message);
        } else {
            window.location.replace("http://localhost:8080/rocp-project/home");
        }
    };

    loginBtnEl.addEventListener("click", handleSubmit);


}

app();