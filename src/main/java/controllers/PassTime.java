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

public class PassTime {
	public static void passTime(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			HttpSession session = req.getSession(false);
			User currentUser = null;
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
				currentUser = (User) session.getAttribute("user");
				if (currentUser.getRole().getRoleId() == 1) {
					List<Account> accounts = bDao.getAllSavingsAccounts();
					double monthlyInterest = 0.05/12;
					JsonNode jsonNode = om.readTree(req.getReader());
					int numMonths = jsonNode.get("numOfMonths").asInt();
					for (Account account : accounts) {
						account.setBalance(account.getBalance() * (Math.pow(1 + monthlyInterest, numMonths)));
						bDao.updateAccount(account);
					}
					res.setStatus(200);
					res.getWriter().write(om.writeValueAsString(new Message(numMonths + " months of interest has been accrued for all Savings Accounts")));
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
