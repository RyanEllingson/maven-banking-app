package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.Message;
import models.Role;
import models.User;
import util.ConnectionFactory;

public class Users {
	public static void getAllUsers(HttpServletRequest req, HttpServletResponse res) {
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
				if (currentUser.getRole().getRoleId() == 1 || currentUser.getRole().getRoleId() == 2) {
					List<User> users = bDao.getAllUsers();
					res.setStatus(200);
					res.getWriter().write(om.writeValueAsString(users));
				} else {
					res.setStatus(401);
					res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateUser(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(req.getReader());
			HttpSession session = req.getSession(false);
			User currentUser = null;
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
//				Check if another user has the requested username already
				User testUser = bDao.getUserByUsername(jsonNode.get("username").asText());
				if ((testUser.getUserId() == 0) || (testUser.getUserId() == jsonNode.get("userId").asInt())) {
					User user = new User(jsonNode.get("userId").asInt(), jsonNode.get("username").asText(), jsonNode.get("password").asText(), jsonNode.get("firstName").asText(), jsonNode.get("lastName").asText(), jsonNode.get("email").asText(), new Role(jsonNode.get("roleId").asInt(), "blah"));
					currentUser = (User) session.getAttribute("user");
					if (currentUser.getRole().getRoleId() == 1 || currentUser.getUserId() == user.getUserId()) {
						bDao.updateUser(user);
						if (currentUser.getUserId() == user.getUserId()) {
							session.removeAttribute("user");
							session.setAttribute("user", bDao.getUserById(user.getUserId()));
						}
						res.setStatus(200);
						res.getWriter().write(om.writeValueAsString(bDao.getUserById(user.getUserId())));
					} else {
						res.setStatus(401);
						res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
					}
				} else {
					res.setStatus(400);
					res.getWriter().write(om.writeValueAsString(new Message("Username already in use")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
