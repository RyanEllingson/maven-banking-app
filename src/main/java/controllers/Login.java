package controllers;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.Message;
import models.User;
import util.ConnectionFactory;

public class Login {
	public static void getLoginPage(HttpServletRequest req, HttpServletResponse res) {
		try {
			RequestDispatcher redir = req.getRequestDispatcher("/Login.html");
			redir.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void login(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(req.getReader());
			String inputUsername = jsonNode.get("username").asText();
			String inputPassword = jsonNode.get("password").asText();
			User user = bDao.getUserByUsername(inputUsername);
			if (inputPassword.equals(user.getPassword())) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				res.setStatus(200);
				res.getWriter().write(om.writeValueAsString(user));
			} else {
				res.setStatus(400);
				res.getWriter().write(om.writeValueAsString(new Message("Invalid Credentials")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
