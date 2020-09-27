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
import models.Role;
import models.User;
import util.ConnectionFactory;

public class Register {
	public static void getRegisterPage(HttpServletRequest req, HttpServletResponse res) {
		try {
			RequestDispatcher redir = req.getRequestDispatcher("/Register.html");
			redir.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void register(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(req.getReader());
			int insertId = bDao.insertUser(new User(0, jsonNode.get("username").asText(), jsonNode.get("password").asText(), jsonNode.get("firstName").asText(), jsonNode.get("lastName").asText(), jsonNode.get("email").asText(), new Role(jsonNode.get("roleId").asInt(), "blah")));
			if (insertId > 0) {
				HttpSession session = req.getSession();
				session.setAttribute("user", bDao.getUserById(insertId));
				res.setStatus(201);
				res.getWriter().write(om.writeValueAsString(bDao.getUserById(insertId)));
			} else {
				res.setStatus(400);
				res.getWriter().write(om.writeValueAsString(new Message("Invalid fields")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
