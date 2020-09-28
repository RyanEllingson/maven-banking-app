# Maven Banking App

Check out the deployed app at https://maven-banking-app.herokuapp.com/home

This project simulates an application used by a bank to manage customers and accounts, as well as the user interface used by customers and employees of the bank to interact with the system.

## Using the App

Upon navigating to the home page, an unauthenticated user will be redirected to the Register page.  To register as a new user, the user enters the desired username, password, first name, last name, and email, and selects what role they want from either Admin, Employee, or Standard.

![screenshot1](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot1.JPG)

After logging in, a bank customer (either Standard or Premium role) can see their user information, as well as any accounts they are associated with.  They can also open a new account by entering the initial balance, selecting the account status (from Pending, Open, Closed, or Denied), and the account type (either Checking or Savings), and finally clicking the "Add Account" button.

![screenshot2](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot2.JPG)

After adding the account it appears in the list of that user's accounts.

![screenshot3](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot3.JPG)

Clicking on the "View Details" button for an account brings up a new view displaying all the users associated with that account, as well as showing a list of options for interacting with the account.  Clicking on the "Deposit," "Withdraw," or "Transfer" buttons opens up a menu that allows the user to perform the corresponding action.  For example to make a deposit, the user clicks the "Deposit" button, enters the dollar amount they would like to deposit, and then clicks "Submit deposit" to complete the transaction.  The user is then taken back to the home screen and can see the updated balance on their account.

![screenshot4](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot4.JPG)

![screenshot5](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot5.JPG)

A user can update their information by first clicking the "Enable/disable edits" button in the user details field, then updating the desired information followed by clicking the "Save changes" button.  In this example the user changes their password from "password" to "betterpassword."

![screenshot6](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot6.JPG)

![screenshot7](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot7.JPG)

![screenshot8](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot8.JPG)

A Standard user can upgrade to be a Premium user.  This upgrade costs $100, and to pay this fee the user must choose which of their accounts the fee will be deducted from.

![screenshot9](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot9.JPG)

![screenshot10](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot10.JPG)

A Premium user is distinguished from a Standard user in that a Premium user can add other users to their accounts to create joint accounts.  In the "View details" screen for an account, clicking the "Add user to account" button opens a menu allowing the user to enter the user ID of the user they would like to add to the account, then click "Add user" to perform the addition.  The account is updated to display both users when the account details are viewed.

![screenshot11](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot11.JPG)

![screenshot12](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot12.JPG)

The user can log out from their account by clicking the "Logout" link in the navbar at the top of the screen.  They are automatically redirected to the Login page, where they can enter their username and password to log in to an existing account.

![screenshot13](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot13.JPG)

When a user logs in as an Admin, their home screen displays all the users and all the accounts currently in the system.  They can view and update information for any user or account by clicking the corresponding "View Details" button.

![screenshot14](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot14.JPG)

Admin users can filter which accounts they see by their current status by clicking the "More options" button to open a dropdown menu, then selecting the status they want and clicking "Get accounts by status."

![screenshot15](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot15.JPG)

Another action Admin users can perform is to "pass time," which simulates the passage of time for the purpose of accruing interest on all savings accounts in the system.  To do this the user enters the number of months of time to pass, then clicks the "Pass time" button.

![screenshot16](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot16.JPG)

Employee users are shown a similar view as Admin users, with the difference being Employee users cannot perform account transactions or update the information of other users.

![screenshot17](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot17.JPG)

![screenshot18](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot18.JPG)

Employee and Admin users can update their own information by clicking the "Edit info" button at the top of the screen.  They cannot, however, open accounts for themselves or upgrade themselves to Premium users.  Only customers may have accounts.

![screenshot19](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot19.JPG)

Finally, Admin users can delete users and accounts from the system.  In the account details screen, clicking the "Delete account" button deletes the account, and removes the account from the list displayed on the screen.

![screenshot20](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot20.JPG)

![screenshot21](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot21.JPG)

In the user details screen, clicking the "Delete user" button deletes the user from the system and removes them from the list of users displayed on the screen.

![screenshot22](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot22.JPG)

![screenshot23](https://github.com/RyanEllingson/maven-banking-app/blob/master/src/main/resources/screenshots/screenshot23.JPG)

## Tools and Technologies Used

The back end for this application is a Maven Java project hosted on an embedded Tomcat server.  The API is built implementing a Front Controller design pattern using a single servlet and a set of Request Helper classes.  The Request Helpers match the request URI and forward the request to the appropriate Controller class which implements the business logic.  The Jackson Databind dependency is utilized to deal with JSON in the HTTP requests and responses.

The application stores data in a PostgreSQL database.  The database interaction is handled using a Data Access Object (DAO) class, which contains all the JDBC logic.  User authentication is handled using session storage.  When the Controller class methods are called by the Request Helpers, the methods first check to see if the request came from an authenticated user, and if so whether that user has the appropriate permission to perform the task requested.  If so, a DAO instance is created and the appropriate method is called.

A Singleton design pattern is applied to the JDBC connection instance.  The first time the application is asked to perform a database operation the ConnectionFactory class instantiates a new database connection.  This connection is stored as a private variable, and is returned every subsequent time a connection is requested.

The front end is built using basic HTML, CSS, and JavaScript.  The Login and Register pages are their own separate files, but all the other content and functionality is contained in the Home page.   Much of the content shown on the screen is created dynamically using JavaScript using JSON responses from the back end, including the options for Role, Account Status and Type in drop-down menus, as well as all user and account information.

## Acknowledgements

This application was built as an assignment for Revature's Online Certification Program (ROCP).  Special thanks to Revature for their training in building Java web applications.
