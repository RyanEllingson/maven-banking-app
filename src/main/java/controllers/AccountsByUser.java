package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.Account;
import models.Message;
import models.User;
import util.ConnectionFactory;

public class AccountsByUser {
	public static void getAccountsByUser(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			String[] params = req.getRequestURI().split("/");
			int userId = Integer.parseInt(params[3]);
			HttpSession session = req.getSession(false);
			User currentUser = null;
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
				currentUser = (User) session.getAttribute("user");
				if (currentUser.getUserId() == userId || currentUser.getRole().getRoleId() == 1 || currentUser.getRole().getRoleId() == 2) {
					List<Account> accounts = bDao.getAccountsByUser(userId);
					res.setStatus(200);
					res.getWriter().write(om.writeValueAsString(accounts));
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
