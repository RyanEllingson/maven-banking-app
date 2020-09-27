package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.Accounts;
import controllers.Users;

public class PutRequestHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res) {
		String uri = req.getRequestURI();
		if (uri.matches("/accounts")) {
			Accounts.updateAccount(req, res);
		} else if (uri.matches("/users")) {
			Users.updateUser(req, res);
		}
	}
}
