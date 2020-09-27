package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.Account;
import models.Message;
import models.User;
import util.ConnectionFactory;

public class Withdraw {
	public static void withdraw(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(req.getReader());
			HttpSession session = req.getSession(false);
			int accountId = jsonNode.get("accountId").asInt();
			double amount = jsonNode.get("amount").asDouble();
			User currentUser = null;
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
				currentUser = (User) session.getAttribute("user");
				List<User> users = bDao.getUsersByAccount(accountId);
				boolean isMatch = false;
				for (User user : users) {
					if (user.getUserId() == currentUser.getUserId()) {
						isMatch = true;
					}
				}
				if (isMatch || currentUser.getRole().getRoleId() == 1) {
					Account account = bDao.getAccountById(accountId);
					account.setBalance(account.getBalance() - amount);
					bDao.updateAccount(account);
					res.setStatus(200);
					res.getWriter().write(om.writeValueAsString(new Message("$" + amount + " has been withdrawn from Account #" + accountId)));
				} else {
					res.setStatus(401);
					res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
