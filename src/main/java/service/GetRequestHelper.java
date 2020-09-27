package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.Accounts;
import controllers.AccountsById;
import controllers.AccountsByStatus;
import controllers.AccountsByUser;
import controllers.CurrentUser;
import controllers.Home;
import controllers.Login;
import controllers.Register;
import controllers.Roles;
import controllers.Statuses;
import controllers.Types;
import controllers.Users;
import controllers.UsersByAccount;
import controllers.UsersById;

public class GetRequestHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res) {
		String uri = req.getRequestURI();
		if (uri.matches("/accounts")) {
			Accounts.getAllAccounts(req, res);
		} else if (uri.matches("/accounts/status/(.*)")) {
			AccountsByStatus.getAccountsByStatus(req, res);
		} else if (uri.matches("/accounts/owner/(.*)")) {
			AccountsByUser.getAccountsByUser(req, res);
		} else if (uri.matches("/accounts/(.*)")) {
			AccountsById.getAccountById(req, res);
		}  else if (uri.matches("/currentuser")) {
			CurrentUser.getCurrentUser(req, res);
		} else if (uri.matches("/home")) {
			Home.getHomePage(req, res);
		} else if (uri.matches("/login")) {
			Login.getLoginPage(req, res);
		} else if (uri.matches("/register")) {
			Register.getRegisterPage(req, res);
		} else if (uri.matches("/roles")) {
			Roles.getRoles(req, res);
		} else if (uri.matches("/statuses")) {
			Statuses.getStatuses(req, res);
		} else if (uri.matches("/types")) {
			Types.getTypes(req, res);
		} else if (uri.matches("/users")) {
			Users.getAllUsers(req, res);
		} else if (uri.matches("/users/account/(.*)")) {
			UsersByAccount.getUsersByAccount(req, res);
		} else if (uri.matches("/users/(.*)")) {
			UsersById.getUserById(req, res);
		}
	}
}
