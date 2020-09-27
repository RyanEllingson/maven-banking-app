package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AccountsById;
import controllers.UsersById;


public class DeleteRequestHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res) {
		String uri = req.getRequestURI();
		if (uri.matches("/accounts/(.*)")) {
			AccountsById.deleteAccountById(req, res);
		} else if (uri.matches("/users/(.*)")) {
			UsersById.deleteUserById(req, res);
		}
	}
}
