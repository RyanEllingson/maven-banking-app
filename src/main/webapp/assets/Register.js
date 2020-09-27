const app = function() {
    const usernameEl = document.getElementById("username-el");
    const passwordEl = document.getElementById("password-el");
    const firstNameEl = document.getElementById("firstname-el");
    const lastNameEl = document.getElementById("lastname-el");
    const emailEl = document.getElementById("email-el");
    const roleEl = document.getElementById("role-el");
    const registerBtnEl = document.getElementById("register-btn");

    const populateRoles = async function() {
        const response = await axios.get("/roles");
        const roles = response.data;
    
        for (let i=0; i<3; i++) {
            const roleOption = document.createElement("option");
            roleOption.setAttribute("value", roles[i].roleId);
            roleOption.innerText = roles[i].role;
            roleEl.append(roleOption);
        }
    }

    const handleSubmit = async function(event) {
        event.preventDefault();
        const user = {
            username: usernameEl.value,
            password: passwordEl.value,
            firstName: firstNameEl.value,
            lastName: lastNameEl.value,
            email: emailEl.value,
            roleId: roleEl.value
        };
        const response = await axios.post("/register", user);
        if (response.data.message) {
            console.log(response.data.message);
        } else {
            window.location.replace("https://maven-banking-app.herokuapp.com/home");
        }
    }

    populateRoles();

    registerBtnEl.addEventListener("click", handleSubmit);

    
};

app();