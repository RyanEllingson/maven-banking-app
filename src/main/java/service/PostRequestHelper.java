package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AccountLinks;
import controllers.Accounts;
import controllers.Deposit;
import controllers.Login;
import controllers.Logout;
import controllers.PassTime;
import controllers.Register;
import controllers.Transfer;
import controllers.Withdraw;

public class PostRequestHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res) {
		String uri = req.getRequestURI();
		if (uri.matches("/accountlinks")) {
			AccountLinks.addAccountLink(req, res);
		} else if (uri.matches("/accounts")) {
			Accounts.addAccount(req, res);
		} else if (uri.matches("/accounts/deposit")) {
			Deposit.deposit(req, res);
		} else if (uri.matches("/login")) {
			Login.login(req, res);
		} else if (uri.matches("/logout")) {
			Logout.logout(req, res);
		} else if (uri.matches("/passTime")) {
			PassTime.passTime(req, res);
		} else if (uri.matches("/register")) {
			Register.register(req, res);
		} else if (uri.matches("/accounts/transfer")) {
			Transfer.transfer(req, res);
		} else if (uri.matches("/accounts/withdraw")) {
			Withdraw.withdraw(req, res);
		}
	}
}
