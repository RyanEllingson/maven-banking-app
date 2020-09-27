package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.AccountLink;
import models.Message;
import models.User;
import util.ConnectionFactory;

public class UsersById {
	public static void getUserById(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			String[] params = req.getRequestURI().split("/");
			User user = bDao.getUserById(Integer.parseInt(params[2]));
			HttpSession session = req.getSession(false);
			User currentUser = null;
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
				currentUser = (User) session.getAttribute("user");
				if (currentUser.getRole().getRoleId() == 1 || currentUser.getRole().getRoleId() == 2 || currentUser.getUserId() == user.getUserId()) {
					res.setStatus(200);
					res.getWriter().write(om.writeValueAsString(user));
				} else {
					res.setStatus(401);
					res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteUserById(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			String[] params = req.getRequestURI().split("/");
			User user = bDao.getUserById(Integer.parseInt(params[2]));
			if (user.getUserId() == 0) {
				res.getWriter().write(om.writeValueAsString(new Message("User doesn't exist")));
			} else {
				List<AccountLink> links = bDao.getLinksByUser(user.getUserId());
				for (AccountLink link : links) {
					bDao.deleteLinkById(link.getId());
				}
				bDao.deleteUserById(user.getUserId());
				res.getWriter().write(om.writeValueAsString(new Message("User successfully deleted")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
