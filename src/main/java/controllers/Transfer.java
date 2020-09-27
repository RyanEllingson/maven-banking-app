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

public class Transfer {
	public static void transfer(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(req.getReader());
			HttpSession session = req.getSession(false);
			int sourceId = jsonNode.get("sourceAccountId").asInt();
			int targetId = jsonNode.get("targetAccountId").asInt();
			double amount = jsonNode.get("amount").asDouble();
			User currentUser = null;
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
				currentUser = (User) session.getAttribute("user");
				List<User> users = bDao.getUsersByAccount(sourceId);
				boolean isMatch = false;
				for (User user : users) {
					if (user.getUserId() == currentUser.getUserId()) {
						isMatch = true;
					}
				}
				if (isMatch || currentUser.getRole().getRoleId() == 1) {
					Account source = bDao.getAccountById(sourceId);
					Account target = bDao.getAccountById(targetId);
					if (source.getAccountId() == 0 || target.getAccountId() == 0) {
						res.setStatus(400);
						res.getWriter().write(om.writeValueAsString(new Message("Invalid account number")));
					} else {
						source.setBalance(source.getBalance() - amount);
						target.setBalance(target.getBalance() + amount);
						bDao.updateAccount(source);
						bDao.updateAccount(target);
						res.setStatus(200);
						res.getWriter().write(om.writeValueAsString(new Message("$" + amount + " has been transferred from Account #" + sourceId + " to Account #" + targetId)));
					}
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
