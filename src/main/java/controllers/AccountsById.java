package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.Account;
import models.AccountLink;
import models.Message;
import models.User;
import util.ConnectionFactory;

public class AccountsById {
	public static void getAccountById(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			String[] params = req.getRequestURI().split("/");
			HttpSession session = req.getSession(false);
			User currentUser = null;
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
				currentUser = (User) session.getAttribute("user");
				Account account = bDao.getAccountById(Integer.parseInt(params[2]));
				List<User> users = bDao.getUsersByAccount(account.getAccountId());
				boolean isMatch = false;
				for (User user : users) {
					if (user.getUserId() == currentUser.getUserId()) {
						isMatch = true;
					}
				}
				if (isMatch || currentUser.getRole().getRoleId() == 1 || currentUser.getRole().getRoleId() == 2) {
					res.setStatus(200);
					res.getWriter().write(om.writeValueAsString(account));
				} else {
					res.setStatus(401);
					res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteAccountById(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			String[] params = req.getRequestURI().split("/");
			Account account = bDao.getAccountById(Integer.parseInt(params[2]));
			HttpSession session = req.getSession(false);
			User currentUser = null;
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
				currentUser = (User) session.getAttribute("user");
				if (currentUser.getRole().getRoleId() == 1) {
					if (account.getAccountId() == 0) {
						res.setStatus(404);
						res.getWriter().write(om.writeValueAsString(new Message("Account doesn't exist")));
					} else {
						List<AccountLink> links = bDao.getLinksByAccount(account.getAccountId());
						for (AccountLink link : links) {
							bDao.deleteLinkById(link.getId());
						}
						bDao.deleteAccountById(account.getAccountId());
						res.setStatus(200);
						res.getWriter().write(om.writeValueAsString(new Message("Account successfully deleted")));
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
