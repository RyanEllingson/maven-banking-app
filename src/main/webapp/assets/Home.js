const app = function() {
    // universal elements
    const logoutBtnEl = document.getElementById("logout-btn");
    const nameEl = document.getElementById("name-el");
    const userIdEl = document.getElementById("userid-el");
    const usernameEl = document.getElementById("username-el");
    const emailEl = document.getElementById("email-el");
    const roleEl = document.getElementById("role-el");
    const editBtn = document.getElementById("edit-btn");
    const moreOptionsBtn = document.getElementById("more-options-btn");
    const moreOptionsForm = document.getElementById("more-options-form");
    const allAccountsBtn = document.getElementById("all-accounts-btn");
    const accountsByStatusEl = document.getElementById("accounts-by-status");
    const accountsByStatusBtn = document.getElementById("accounts-by-status-btn");
    const passTimeEl = document.getElementById("pass-time-el");
    const passTimeBtn = document.getElementById("pass-time-btn");
    // admin elements
    const adminContentEl = document.getElementById("admin-content");
    const adminUserContainerEl = document.getElementById("admin-user-container");
    const adminAccountContainerEl = document.getElementById("admin-account-container");
    // user info elements
    const userInfoEl = document.getElementById("user-info");
    const userInfoHeaderEl = document.getElementById("user-info-header");
    const userInfoUsernameEl = document.getElementById("user-info-username");
    const userInfoBackBtnContainerEl = document.getElementById("user-info-backbtn-container");
    const userInfoPasswordEl = document.getElementById("user-info-password");
    const userInfoFirstNameEl = document.getElementById("user-info-firstname");
    const userInfoLastNameEl = document.getElementById("user-info-lastname");
    const userInfoEmailEl = document.getElementById("user-info-email");
    const userInfoRoleEl = document.getElementById("user-info-role");
    const userInfoBtnContainerEl = document.getElementById("user-info-btn-container");
    const userInfoEditBtnContainerEl = document.getElementById("user-info-editbtn-container");
    const userInfoSaveBtnContainerEl = document.getElementById("user-info-savebtn-container");
    const userInfoDeleteBtnContainerEl = document.getElementById("user-info-deletebtn-container");
    const userInfoAccountHeaderEl = document.getElementById("user-info-account-header");
    const userInfoAccountContainerEl = document.getElementById("user-info-account-container");
    const newAccountCard = document.getElementById("new-account-card");
    const newAccountBalanceEl = document.getElementById("new-account-balance");
    const newAccountStatusEl = document.getElementById("new-account-status");
    const newAccountTypeEl = document.getElementById("new-account-type");
    const addAccountBtnContainerEl = document.getElementById("add-account-btn-container");
    const upgradeContainerEl = document.getElementById("upgrade-container");
    const upgradeUserEl = document.getElementById("upgrade-premium");
    const upgradeUserBtnContainerEl = document.getElementById("upgrade-btn-container");
    // account info elements
    const accountInfoEl = document.getElementById("account-info");
    const accountInfoUserHeaderEl = document.getElementById("account-info-user-header");
    const accountInfoUserContainerEl = document.getElementById("account-info-user-container");
    const accountInfoAccountHeaderEl = document.getElementById("account-info-account-header");
    const accountInfoBackBtn = document.getElementById("account-info-backbtn");
    const accountInfoBalanceEl = document.getElementById("account-info-balance");
    const accountInfoStatusEl = document.getElementById("account-info-status");
    const accountInfoTypeEl = document.getElementById("account-info-type");
    const modifyAccountBtnContainerEl = document.getElementById("modify-account-btn-container");
    const actOnAccountBtnContainerEl = document.getElementById("act-on-account-btn-container");
    const accountInfoEditBtnContainerEl = document.getElementById("account-info-editbtn-container");
    const accountInfoSaveBtnContainerEl = document.getElementById("account-info-savebtn-container");
    const accountInfoDeleteBtnContainerEl = document.getElementById("account-info-deletebtn-container");
    const depositAmountEl = document.getElementById("deposit-amount");
    const depositSubmitBtnContainerEl = document.getElementById("deposit-submitbtn-container");
    const withdrawAmountEl = document.getElementById("withdraw-amount");
    const withdrawSubmitBtnContainerEl = document.getElementById("withdraw-submitbtn-container");
    const transferAmountEl = document.getElementById("transfer-amount");
    const transferTargetEl = document.getElementById("transfer-target");
    const transferSubmitBtnContainerEl = document.getElementById("transfer-submitbtn-container");
    const addUserToggle = document.getElementById("add-user-toggle");
    const addedUserEl = document.getElementById("added-user");
    const addUserSubmitBtnContainer = document.getElementById("add-user-submitbtn-container");

    let currentUser = null;

    const loadPage = async function() {
        console.log("loading the page");
        currentUser = null;
        const response = await axios.get("/currentuser");
        currentUser = response.data;
        console.log(currentUser);
        if (!currentUser) {
            window.location.replace("https://maven-banking-app.herokuapp.com/login");
        } else {
            nameEl.innerText = `Welcome ${currentUser.firstName} ${currentUser.lastName}!`;
            userIdEl.innerText =   `User Id: ${currentUser.userId}`;
            usernameEl.innerText = `Username: ${currentUser.username}`;
            emailEl.innerText = `Email: ${currentUser.email}`;
            roleEl.innerText = `Role: ${currentUser.role.role}`;
            switch(currentUser.role.roleId) {
                case 1:
                    editBtn.setAttribute("class", "btn btn-success");
                    moreOptionsBtn.setAttribute("class", "btn btn-primary");
                    moreOptionsForm.setAttribute("class", "collapse");
                    passTimeEl.setAttribute("class", "form-control");
                    passTimeBtn.setAttribute("class", "btn btn-success");
                    loadPageAdmin();
                    break;
                case 2:
                    editBtn.setAttribute("class", "btn btn-success");
                    moreOptionsBtn.setAttribute("class", "btn btn-primary");
                    moreOptionsForm.setAttribute("class", "collapse");
                    passTimeEl.setAttribute("class", "form-control d-none");
                    passTimeBtn.setAttribute("class", "btn btn-success d-none");
                    loadPageAdmin();
                    break;
                case 3:
                    editBtn.setAttribute("class", "btn btn-success d-none");
                    moreOptionsBtn.setAttribute("class", "btn btn-primary d-none");
                    moreOptionsForm.setAttribute("class", "collapse d-none");
                    passTimeEl.setAttribute("class", "form-control d-none");
                    passTimeBtn.setAttribute("class", "btn btn-success d-none");
                    loadUserInfo({preventDefault: function() {}}, currentUser);
                    break;
                case 4:
                    editBtn.setAttribute("class", "btn btn-success d-none");
                    moreOptionsBtn.setAttribute("class", "btn btn-primary d-none");
                    moreOptionsForm.setAttribute("class", "collapse d-none");
                    passTimeEl.setAttribute("class", "form-control d-none");
                    passTimeBtn.setAttribute("class", "btn btn-success d-none");
                    loadUserInfo({preventDefault: function() {}}, currentUser);
                    break;
                default:
                    break;
            }
        }
    }

    const loadPageAdmin = async function() {
        adminContentEl.setAttribute("class", "row");
        userInfoEl.setAttribute("class", "row d-none");
        accountInfoEl.setAttribute("class", "row d-none");
        const userResponse = await axios.get("/users");
        const users = userResponse.data;
        adminUserContainerEl.innerHTML = "";
        for (let user of users) {
            if (user.userId === currentUser.userId) {
                continue;
            }
            const userContainer = document.createElement("li");
            userContainer.setAttribute("class", "list-group-item");
            adminUserContainerEl.append(userContainer);

            const row1 = document.createElement("div");
            row1.setAttribute("class", "row mb-1");
            userContainer.append(row1);

            const col1 = document.createElement("div");
            col1.setAttribute("class", "col-8");
            row1.append(col1);

            const nameEl = document.createElement("h5");
            nameEl.setAttribute("class", "card-title");
            nameEl.innerText = `${user.firstName} ${user.lastName}`;
            col1.append(nameEl);

            const col2 = document.createElement("div");
            col2.setAttribute("class", "col-4");
            row1.append(col2);

            const viewBtnEl = document.createElement("button");
            viewBtnEl.setAttribute("class", "btn btn-success");
            viewBtnEl.innerText = "View Details";
            viewBtnEl.addEventListener("click", (e) => {loadUserInfo(e, user)});
            col2.append(viewBtnEl);

            const row2 = document.createElement("div");
            row2.setAttribute("class", "row mb-1");
            userContainer.append(row2);

            const col3 = document.createElement("div");
            col3.setAttribute("class", "col-8");
            row2.append(col3);

            const userIdEl = document.createElement("p");
            userIdEl.setAttribute("class", "card-text");
            userIdEl.innerText = `User Id: ${user.userId}`;
            col3.append(userIdEl);

            const col4 = document.createElement("div");
            col4.setAttribute("class", "col-4");
            row2.append(col4);

            const usernameEl = document.createElement("p");
            usernameEl.setAttribute("class", "card-text");
            usernameEl.innerText = `Username: ${user.username}`;
            col4.append(usernameEl);

            const row3 = document.createElement("div");
            row3.setAttribute("class", "row");
            userContainer.append(row3);

            const col5 = document.createElement("div");
            col5.setAttribute("class", "col-8");
            row3.append(col5);

            const emailEl = document.createElement("p");
            emailEl.setAttribute("class", "card-text");
            emailEl.innerText = `Email: ${user.email}`;
            col5.append(emailEl);

            const col6 = document.createElement("div");
            col6.setAttribute("class", "col-4");
            row3.append(col6);

            const roleEl = document.createElement("p");
            roleEl.setAttribute("class", "card-text");
            roleEl.innerText = `Role: ${user.role.role}`;
            col6.append(roleEl);
        }
        const accountResponse = await axios.get("/accounts");
        const accounts = accountResponse.data;
        adminAccountContainerEl.innerHTML = "";
        for (let account of accounts) {
            const accountContainer = document.createElement("li");
            accountContainer.setAttribute("class", "list-group-item");
            adminAccountContainerEl.append(accountContainer);

            const row1 = document.createElement("div");
            row1.setAttribute("class", "row mb-1");
            accountContainer.append(row1);

            const col1 = document.createElement("div");
            col1.setAttribute("class", "col-8");
            row1.append(col1);

            const accountIdEl = document.createElement("h5");
            accountIdEl.setAttribute("class", "card-title");
            accountIdEl.innerText = `Account Id: ${account.accountId}`;
            col1.append(accountIdEl);

            const col2 = document.createElement("div");
            col2.setAttribute("class", "col-4");
            row1.append(col2);

            const viewBtnEl = document.createElement("button");
            viewBtnEl.setAttribute("class", "btn btn-success");
            viewBtnEl.innerText = "View Details";
            viewBtnEl.addEventListener("click", (e) => {loadAccountInfo(e, account)});
            col2.append(viewBtnEl);

            const row2 = document.createElement("div");
            row2.setAttribute("class", "row");
            accountContainer.append(row2);

            const col3 = document.createElement("div");
            col3.setAttribute("class", "col-4");
            row2.append(col3);

            const balanceEl = document.createElement("p");
            balanceEl.setAttribute("class", "card-text");
            balanceEl.innerText = `Balance: $${(Math.round(account.balance * 100))/100}`;
            col3.append(balanceEl);

            const col4 = document.createElement("div");
            col4.setAttribute("class", "col-4");
            row2.append(col4);

            const statusEl = document.createElement("p");
            statusEl.setAttribute("class", "card-text");
            statusEl.innerText = `Status: ${account.status.status}`;
            col4.append(statusEl);

            const col5 = document.createElement("div");
            col5.setAttribute("class", "col-4");
            row2.append(col5);

            const typeEl = document.createElement("p");
            typeEl.setAttribute("class", "card-text");
            typeEl.innerText = `Type: ${account.type.type}`;
            col5.append(typeEl);
        }
    };

    const loadUserInfo = async function(event, user) {
        event.preventDefault();
        adminContentEl.setAttribute("class", "row d-none");
        accountInfoEl.setAttribute("class", "row d-none");
        userInfoEl.setAttribute("class", "row");
        userInfoHeaderEl.innerText = `Details for User Id ${user.userId}`;
        userInfoUsernameEl.value = user.username;
        userInfoUsernameEl.setAttribute("readonly", true);
        userInfoPasswordEl.value = user.password;
        userInfoPasswordEl.setAttribute("readonly", true);
        userInfoFirstNameEl.value = user.firstName;
        userInfoFirstNameEl.setAttribute("readonly", true);
        userInfoLastNameEl.value = user.lastName;
        userInfoLastNameEl.setAttribute("readonly", true);
        userInfoEmailEl.value = user.email;
        userInfoEmailEl.setAttribute("readonly", true);
        userInfoRoleEl.value = user.role.role;

        userInfoBackBtnContainerEl.innerHTML = "";
        if (currentUser.role.roleId == 1 || currentUser.role.roleId == 2) {
            const userInfoBackBtn = document.createElement("button");
            userInfoBackBtn.setAttribute("class", "btn btn-success ml-auto");
            userInfoBackBtn.innerText = "Back home";
            userInfoBackBtn.addEventListener("click", (e) => {
                e.preventDefault();
                userInfoEl.setAttribute("class", "row d-none");
                loadPage();
            });
            userInfoBackBtnContainerEl.append(userInfoBackBtn);
        }
        
        // Employee can only modify their own information
        if (currentUser.role.roleId == 2 && user.userId != currentUser.userId) {
            userInfoSaveBtnContainerEl.innerHTML = "";
            userInfoEditBtnContainerEl.innerHTML = "";
            userInfoDeleteBtnContainerEl.innerHTML = "";
            userInfoBtnContainerEl.setAttribute("class", "row d-none");
        } else {
            userInfoBtnContainerEl.setAttribute("class", "row");
            userInfoSaveBtnContainerEl.innerHTML = "";
            const userInfoSaveBtn = document.createElement("button");
            userInfoSaveBtn.setAttribute("class", "btn btn-info");
            userInfoSaveBtn.setAttribute("disabled", true);
            userInfoSaveBtn.innerText = "Save changes";
            userInfoSaveBtn.addEventListener("click", (e) => {handleUpdateUser(e, user)});
            userInfoSaveBtnContainerEl.append(userInfoSaveBtn);
    
            userInfoEditBtnContainerEl.innerHTML = "";
            const userInfoEditBtn = document.createElement("button");
            userInfoEditBtn.setAttribute("class", "btn btn-secondary");
            userInfoEditBtn.innerText = "Enable/disable edits";
            userInfoEditBtn.addEventListener("click", (e) => {
                e.preventDefault();
                userInfoUsernameEl.toggleAttribute("readonly");
                userInfoPasswordEl.toggleAttribute("readonly");
                userInfoFirstNameEl.toggleAttribute("readonly");
                userInfoLastNameEl.toggleAttribute("readonly");
                userInfoEmailEl.toggleAttribute("readonly");
                userInfoSaveBtn.toggleAttribute("disabled");
            });
            userInfoEditBtnContainerEl.append(userInfoEditBtn);
            
            userInfoDeleteBtnContainerEl.innerHTML = "";
            if (user.userId != currentUser.userId) {
                const userInfoDeleteBtn = document.createElement("button");
                userInfoDeleteBtn.setAttribute("class", "btn btn-danger");
                userInfoDeleteBtn.innerText = "Delete user";
                userInfoDeleteBtn.addEventListener("click", (e) => {handleDeleteUser(e, user)});
                userInfoDeleteBtnContainerEl.append(userInfoDeleteBtn);
            }
        }

        
        // only account owner or admin may upgrade to premium, and only if user is currently standard
        if (user.role.roleId == 3 && (currentUser.role.roleId == 1 || currentUser.userId == user.userId)) {
            upgradeContainerEl.setAttribute("class", "row");
            upgradeUserBtnContainerEl.innerHTML = "";
            const upgradeUserBtn = document.createElement("button");
            upgradeUserBtn.setAttribute("class", "btn btn-success");
            upgradeUserBtn.innerText = "Upgrade to Premium!";
            upgradeUserBtn.addEventListener("click", (e) => {handleUpgradeUser(e, user, upgradeUserEl.value)});
            upgradeUserBtnContainerEl.append(upgradeUserBtn);
        } else {
            upgradeUserBtnContainerEl.innerHTML = "";
            upgradeContainerEl.setAttribute("class", "row d-none");
        }
        

        userInfoAccountHeaderEl.innerText = `Accounts for User Id ${user.userId}`;
        const accountResponse = await axios.get(`/accounts/owner/${user.userId}`);
        const accounts = accountResponse.data;
        userInfoAccountContainerEl.innerHTML = "";
        for (let account of accounts) {
            const accountContainer = document.createElement("li");
            accountContainer.setAttribute("class", "list-group-item");
            userInfoAccountContainerEl.append(accountContainer);

            const row1 = document.createElement("div");
            row1.setAttribute("class", "row mb-1");
            accountContainer.append(row1);

            const col1 = document.createElement("div");
            col1.setAttribute("class", "col-8");
            row1.append(col1);

            const accountIdEl = document.createElement("h5");
            accountIdEl.setAttribute("class", "card-title");
            accountIdEl.innerText = `Account Id: ${account.accountId}`;
            col1.append(accountIdEl);

            const col2 = document.createElement("div");
            col2.setAttribute("class", "col-4");
            row1.append(col2);

            const viewBtnEl = document.createElement("button");
            viewBtnEl.setAttribute("class", "btn btn-success");
            viewBtnEl.innerText = "View Details";
            viewBtnEl.addEventListener("click", (e) => {loadAccountInfo(e, account)});
            col2.append(viewBtnEl);

            const row2 = document.createElement("div");
            row2.setAttribute("class", "row");
            accountContainer.append(row2);

            const col3 = document.createElement("div");
            col3.setAttribute("class", "col-4");
            row2.append(col3);

            const balanceEl = document.createElement("p");
            balanceEl.setAttribute("class", "card-text");
            balanceEl.innerText = `Balance: $${(Math.round(account.balance * 100))/100}`;
            col3.append(balanceEl);

            const col4 = document.createElement("div");
            col4.setAttribute("class", "col-4");
            row2.append(col4);

            const statusEl = document.createElement("p");
            statusEl.setAttribute("class", "card-text");
            statusEl.innerText = `Status: ${account.status.status}`;
            col4.append(statusEl);

            const col5 = document.createElement("div");
            col5.setAttribute("class", "col-4");
            row2.append(col5);

            const typeEl = document.createElement("p");
            typeEl.setAttribute("class", "card-text");
            typeEl.innerText = `Type: ${account.type.type}`;
            col5.append(typeEl);
        }

        if (user.role.roleId == 3 || user.role.roleId == 4) {
            newAccountCard.setAttribute("class", "card");
        } else {
            newAccountCard.setAttribute("class", "card d-none");
        }

        const statusResponse = await axios.get("/statuses");
        const statuses = statusResponse.data;
        newAccountStatusEl.innerHTML = "";
        for (let status of statuses) {
            const statusOption = document.createElement("option");
            statusOption.setAttribute("value", status.statusId);
            statusOption.innerText = status.status;
            newAccountStatusEl.append(statusOption);
        }

        const typeResponse = await axios.get("/types");
        const types = typeResponse.data;
        newAccountTypeEl.innerHTML = "";
        for (let type of types) {
            const typeOption = document.createElement("option");
            typeOption.setAttribute("value", type.typeId);
            typeOption.innerText = type.type;
            newAccountTypeEl.append(typeOption);
        }

        addAccountBtnContainerEl.innerHTML = "";
        const addAccountBtn = document.createElement("button");
        addAccountBtn.setAttribute("class", "btn btn-success");
        addAccountBtn.innerText = "Add account";
        addAccountBtn.addEventListener("click", (e) => {handleAddAccount(e, user, newAccountBalanceEl.value, newAccountStatusEl.value, newAccountTypeEl.value)});
        addAccountBtnContainerEl.append(addAccountBtn);
    };

    const loadAccountInfo = async function(event, account) {
        event.preventDefault();
        adminContentEl.setAttribute("class", "row d-none");
        userInfoEl.setAttribute("class", "row d-none");
        accountInfoEl.setAttribute("class", "row");
        accountInfoUserHeaderEl.innerText = `Users for Account Id ${account.accountId}`;
        accountInfoUserContainerEl.innerHTML = "";
        const userResponse = await axios.get(`/users/account/${account.accountId}`);
        const users = userResponse.data;
        for (let user of users) {
            const userContainer = document.createElement("li");
            userContainer.setAttribute("class", "list-group-item");
            accountInfoUserContainerEl.append(userContainer);

            const row1 = document.createElement("div");
            row1.setAttribute("class", "row mb-1");
            userContainer.append(row1);

            const col1 = document.createElement("div");
            col1.setAttribute("class", "col-8");
            row1.append(col1);

            const nameEl = document.createElement("h5");
            nameEl.setAttribute("class", "card-title");
            nameEl.innerText = `${user.firstName} ${user.lastName}`;
            col1.append(nameEl);

            const col2 = document.createElement("div");
            col2.setAttribute("class", "col-4");
            row1.append(col2);

            // only view details on other users if an admin or employee
            if (currentUser.role.roleId == 1 || currentUser.role.roleId == 2) {
                const viewBtnEl = document.createElement("button");
                viewBtnEl.setAttribute("class", "btn btn-success");
                viewBtnEl.innerText = "View Details";
                viewBtnEl.addEventListener("click", (e) => loadUserInfo(e, user));
                col2.append(viewBtnEl);
            }
            

            const row2 = document.createElement("div");
            row2.setAttribute("class", "row mb-1");
            userContainer.append(row2);

            const col3 = document.createElement("div");
            col3.setAttribute("class", "col-8");
            row2.append(col3);

            const userIdEl = document.createElement("p");
            userIdEl.setAttribute("class", "card-text");
            userIdEl.innerText = `User Id: ${user.userId}`;
            col3.append(userIdEl);

            const col4 = document.createElement("div");
            col4.setAttribute("class", "col-4");
            row2.append(col4);

            const usernameEl = document.createElement("p");
            usernameEl.setAttribute("class", "card-text");
            usernameEl.innerText = `Username: ${user.username}`;
            col4.append(usernameEl);

            const row3 = document.createElement("div");
            row3.setAttribute("class", "row");
            userContainer.append(row3);

            const col5 = document.createElement("div");
            col5.setAttribute("class", "col-8");
            row3.append(col5);

            const emailEl = document.createElement("p");
            emailEl.setAttribute("class", "card-text");
            emailEl.innerText = `Email: ${user.email}`;
            col5.append(emailEl);

            const col6 = document.createElement("div");
            col6.setAttribute("class", "col-4");
            row3.append(col6);

            const roleEl = document.createElement("p");
            roleEl.setAttribute("class", "card-text");
            roleEl.innerText = `Role: ${user.role.role}`;
            col6.append(roleEl);
        }
        accountInfoAccountHeaderEl.innerText = `Details for Account Id ${account.accountId}`;
        accountInfoBalanceEl.value = `$${(Math.round(account.balance * 100))/100}`;
        const statusResponse = await axios.get("/statuses");
        const statuses = statusResponse.data;
        accountInfoStatusEl.innerHTML = "";
        for (let status of statuses) {
            const statusOption = document.createElement("option");
            statusOption.setAttribute("value", status.statusId);
            statusOption.innerText = status.status;
            accountInfoStatusEl.append(statusOption);
        }
        accountInfoStatusEl.value = account.status.statusId;
        accountInfoStatusEl.setAttribute("disabled", true);
        accountInfoTypeEl.value = account.type.type;
        accountInfoBackBtn.addEventListener("click", (e) => {
            e.preventDefault();
            accountInfoEl.setAttribute("class", "row d-none");
            loadPage();
        });

        if (currentUser.role.roleId == 2) {
            modifyAccountBtnContainerEl.setAttribute("class", "row mb-3 d-none");
            actOnAccountBtnContainerEl.setAttribute("class", "row mb-3 d-none");
        } else if (currentUser.role.roleId == 1) {
            modifyAccountBtnContainerEl.setAttribute("class", "row mb-3");
            actOnAccountBtnContainerEl.setAttribute("class", "row mb-3");
        } else {
            modifyAccountBtnContainerEl.setAttribute("class", "row mb-3 d-none");
            actOnAccountBtnContainerEl.setAttribute("class", "row mb-3");
        }

        accountInfoSaveBtnContainerEl.innerHTML = "";
        const accountInfoSaveBtn = document.createElement("button");
        accountInfoSaveBtn.setAttribute("class", "btn btn-info");
        accountInfoSaveBtn.setAttribute("disabled", true);
        accountInfoSaveBtn.innerText = "Save changes";
        accountInfoSaveBtn.addEventListener("click", (e) => {handleUpdateAccount(e, account)});
        accountInfoSaveBtnContainerEl.append(accountInfoSaveBtn);

        accountInfoEditBtnContainerEl.innerHTML = "";
        const accountInfoEditBtn = document.createElement("button");
        accountInfoEditBtn.setAttribute("class", "btn btn-secondary");
        accountInfoEditBtn.innerText = "Enable/disable edits";
        accountInfoEditBtn.addEventListener("click", (e) => {
            e.preventDefault();
            accountInfoStatusEl.toggleAttribute("disabled");
            accountInfoSaveBtn.toggleAttribute("disabled");
        });
        accountInfoEditBtnContainerEl.append(accountInfoEditBtn);

        accountInfoDeleteBtnContainerEl.innerHTML = "";
        const accountInfoDeleteBtn = document.createElement("button");
        accountInfoDeleteBtn.setAttribute("class", "btn btn-danger");
        accountInfoDeleteBtn.innerText = "Delete account";
        accountInfoDeleteBtn.addEventListener("click", (e) => {handleDeleteAccount(e, account)});
        accountInfoDeleteBtnContainerEl.append(accountInfoDeleteBtn);

        depositSubmitBtnContainerEl.innerHTML = "";
        const depositSubmitBtn = document.createElement("button");
        depositSubmitBtn.setAttribute("class", "btn btn-success my-auto");
        depositSubmitBtn.innerText = "Submit deposit";
        depositSubmitBtn.addEventListener("click", (e) => {handleDeposit(e, account.accountId, depositAmountEl.value)});
        depositSubmitBtnContainerEl.append(depositSubmitBtn);

        withdrawSubmitBtnContainerEl.innerHTML = "";
        const withdrawSubmitBtn = document.createElement("button");
        withdrawSubmitBtn.setAttribute("class", "btn btn-success my-auto");
        withdrawSubmitBtn.innerText = "Submit withdrawal";
        withdrawSubmitBtn.addEventListener("click", (e) => {handleWithdraw(e, account.accountId, withdrawAmountEl.value)});
        withdrawSubmitBtnContainerEl.append(withdrawSubmitBtn);

        transferSubmitBtnContainerEl.innerHTML = "";
        const transferSubmitBtn = document.createElement("button");
        transferSubmitBtn.setAttribute("class", "btn btn-success my-auto");
        transferSubmitBtn.innerText = "Submit transfer";
        transferSubmitBtn.addEventListener("click", (e) => {handleTransfer(e, account.accountId, transferTargetEl.value, transferAmountEl.value)});
        transferSubmitBtnContainerEl.append(transferSubmitBtn);

        // standard user and employee cannot add user to an account
        if (currentUser.role.roleId == 1 || currentUser.role.roleId == 4) {
            addUserToggle.setAttribute("class", "btn btn-primary");
            addUserSubmitBtnContainer.innerHTML = "";
            const addUserSubmitBtn = document.createElement("button");
            addUserSubmitBtn.setAttribute("class", "btn btn-success my-auto");
            addUserSubmitBtn.innerText = "Add user";
            addUserSubmitBtn.addEventListener("click", (e) => {handleAddUserToAccount(e, addedUserEl.value, account)});
            addUserSubmitBtnContainer.append(addUserSubmitBtn);
        } else {
            addUserSubmitBtnContainer.innerHTML = "";
            addUserToggle.setAttribute("class", "btn btn-primary d-none");
        }
        
    };

    const handleUpdateUser = async function(event, user) {
        event.preventDefault();
        try {
            const response = axios.put("/users", {
                userId: user.userId,
                username: userInfoUsernameEl.value,
                password: userInfoPasswordEl.value,
                firstName: userInfoFirstNameEl.value,
                lastName: userInfoLastNameEl.value,
                email: userInfoEmailEl.value,
                roleId: user.role.roleId
            });
        } catch (err) {
            console.log(err);
        }
        userInfoEl.setAttribute("class", "row d-none");
        if (user.userId === currentUser.userId) {
            window.location.replace("https://maven-banking-app.herokuapp.com/home");
        } else {
            loadPage();
        }
    }

    const handleDeleteUser = async function(event, user) {
        event.preventDefault();
        try {
            const response = axios.delete(`/users/${user.userId}`);
        } catch (err) {
            console.log(err);
        }
        userInfoEl.setAttribute("class", "row d-none");
        window.location.replace("https://maven-banking-app.herokuapp.com/home");
    }

    const handleUpgradeUser = async function(event, user, accountId) {
        event.preventDefault();
        const withdrawResponse = await axios.post("/accounts/withdraw", {
            accountId,
            amount: 100
        });
        if (withdrawResponse.data.message) {
            const {userId, username, password, firstName, lastName, email} = user;
            const updateResponse = await axios.put("/users", {
                userId,
                username,
                password,
                firstName,
                lastName,
                email,
                roleId: 4
            });
            userInfoEl.setAttribute("class", "row d-none");
            loadPage();
        }
    };

    const handleAddAccount = async function(event, user, balance, statusId, typeId) {
        event.preventDefault();
        try {
            const accountResponse = await axios.post("/accounts", {
                balance,
                statusId,
                typeId
            });
            console.log(accountResponse);
            const userId = user.userId;
            const accountId = accountResponse.data.accountId;
            const linkResponse = await axios.post("/accountlinks", {
                userId,
                accountId
            });
        } catch (err) {
            console.log(err);
        }
        userInfoEl.setAttribute("class", "row d-none");
        loadPage();
    }

    const handleUpdateAccount = async function(event, account) {
        event.preventDefault();
        try {
            const response = await axios.put("/accounts", {
                accountId: account.accountId,
                balance: account.balance,
                statusId: accountInfoStatusEl.value,
                typeId: account.type.typeId
            });
            console.log("updated account:");
            console.log(account);
            accountInfoEl.setAttribute("class", "row d-none");
            loadPage();
        } catch (err) {
            console.log(err);
        }
    };

    const handleDeleteAccount = async function(event, account) {
        event.preventDefault();
        try {
            const response = axios.delete(`/accounts/${account.accountId}`);
        } catch (err) {
            console.log(err);
        }
        accountInfoEl.setAttribute("class", "row d-none");
        loadPage();
    };

    const handleDeposit = async function(event, accountId, amount) {
        event.preventDefault();
        try {
            const response = await axios.post("/accounts/deposit", {
                accountId,
                amount
            });
        } catch (err) {
            console.log(err);
        }
        accountInfoEl.setAttribute("class", "row d-none");
        loadPage();
    };

    const handleWithdraw = async function(event, accountId, amount) {
        event.preventDefault();
        try {
            const response = await axios.post("/accounts/withdraw", {
                accountId,
                amount
            });
        } catch (err) {
            console.log(err);
        }
        accountInfoEl.setAttribute("class", "row d-none");
        loadPage();
    };

    const handleTransfer = async function(event, sourceAccountId, targetAccountId, amount) {
        event.preventDefault();
        try {
            const response = await axios.post("/accounts/transfer", {
                sourceAccountId,
                targetAccountId,
                amount
            });
        } catch (err) {
            console.log(err);
        }
        accountInfoEl.setAttribute("class", "row d-none");
        loadPage();
    }

    const handleAddUserToAccount = async function(event, userId, account) {
        event.preventDefault();
        console.log("userId: " + userId);
        console.log("accountId: " + account.accountId);
        try {
            const response = await axios.post("/accountlinks", {
                userId,
                accountId: account.accountId
            });
            console.log(response);
        } catch (err) {
            console.log(err);
        }
        accountInfoEl.setAttribute("class", "row d-none");
        loadPage();
    };

    const handleLogout = async function(event) {
        event.preventDefault();
        const response = await axios.post("/logout", {});
        window.location.replace("https://maven-banking-app.herokuapp.com/login");
    }

    const loadStatuses = async function() {
        const statusResponse = await axios.get("/statuses");
        const statuses = statusResponse.data;
        for (let status of statuses) {
            const statusOption = document.createElement("option");
            statusOption.setAttribute("value", status.statusId);
            statusOption.innerText = status.status;
            accountsByStatusEl.append(statusOption);
        }
    };

    const getAccountsByStatus = async function(event, statusId) {
        event.preventDefault();
        adminAccountContainerEl.innerHTML = "";
        const accountResponse = await axios.get(`/accounts/status/${statusId}`);
        const accounts = accountResponse.data;
        for (let account of accounts) {
            const accountContainer = document.createElement("li");
            accountContainer.setAttribute("class", "list-group-item");
            adminAccountContainerEl.append(accountContainer);

            const row1 = document.createElement("div");
            row1.setAttribute("class", "row mb-1");
            accountContainer.append(row1);

            const col1 = document.createElement("div");
            col1.setAttribute("class", "col-8");
            row1.append(col1);

            const accountIdEl = document.createElement("h5");
            accountIdEl.setAttribute("class", "card-title");
            accountIdEl.innerText = `Account Id: ${account.accountId}`;
            col1.append(accountIdEl);

            const col2 = document.createElement("div");
            col2.setAttribute("class", "col-4");
            row1.append(col2);

            const viewBtnEl = document.createElement("button");
            viewBtnEl.setAttribute("class", "btn btn-success");
            viewBtnEl.innerText = "View Details";
            viewBtnEl.addEventListener("click", (e) => {loadAccountInfo(e, account)});
            col2.append(viewBtnEl);

            const row2 = document.createElement("div");
            row2.setAttribute("class", "row");
            accountContainer.append(row2);

            const col3 = document.createElement("div");
            col3.setAttribute("class", "col-4");
            row2.append(col3);

            const balanceEl = document.createElement("p");
            balanceEl.setAttribute("class", "card-text");
            balanceEl.innerText = `Balance: $${(Math.round(account.balance * 100))/100}`;
            col3.append(balanceEl);

            const col4 = document.createElement("div");
            col4.setAttribute("class", "col-4");
            row2.append(col4);

            const statusEl = document.createElement("p");
            statusEl.setAttribute("class", "card-text");
            statusEl.innerText = `Status: ${account.status.status}`;
            col4.append(statusEl);

            const col5 = document.createElement("div");
            col5.setAttribute("class", "col-4");
            row2.append(col5);

            const typeEl = document.createElement("p");
            typeEl.setAttribute("class", "card-text");
            typeEl.innerText = `Type: ${account.type.type}`;
            col5.append(typeEl);
        }
    }

    const passTime = async function(event) {
        event.preventDefault();
        try {
            const response = await axios.post("/passTime", {
                numOfMonths: passTimeEl.value
            });
        } catch (err) {
            console.log(err);
        }
        loadPage();
    }

    loadPage();
    editBtn.addEventListener("click", (e) => {loadUserInfo(e, currentUser)});
    logoutBtnEl.addEventListener("click", handleLogout);
    allAccountsBtn.addEventListener("click", (e) => {
        e.preventDefault();
        loadPage();
    });
    loadStatuses();
    accountsByStatusBtn.addEventListener("click", (e) => {getAccountsByStatus(e, accountsByStatusEl.value)});
    passTimeBtn.addEventListener("click", passTime);
};

app();