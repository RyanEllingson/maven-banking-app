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
import models.AccountStatus;
import models.AccountType;
import models.Message;
import models.User;
import util.ConnectionFactory;

public class Accounts {
	public static void getAllAccounts(HttpServletRequest req, HttpServletResponse res) {
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
					List<Account> accounts = bDao.getAllAccounts();
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
	
	public static void addAccount(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(req.getReader());
			HttpSession session = req.getSession(false);
			if (session == null) {
				res.setStatus(401);
				res.getWriter().write(om.writeValueAsString(new Message("The requested action is not permitted")));
			} else {
				int insertId = bDao.insertAccount(new Account(0, jsonNode.get("balance").asDouble(), new AccountStatus(jsonNode.get("statusId").asInt(), "blah"), new AccountType(jsonNode.get("typeId").asInt(), "blah")));
				res.setStatus(201);
				res.getWriter().write(om.writeValueAsString(bDao.getAccountById(insertId)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateAccount(HttpServletRequest req, HttpServletResponse res) {
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
				currentUser = (User) session.getAttribute("user");
				if (currentUser.getRole().getRoleId() == 1) {
					Account account = new Account(jsonNode.get("accountId").asInt(), jsonNode.get("balance").asDouble(), new AccountStatus(jsonNode.get("statusId").asInt(), "blah"), new AccountType(jsonNode.get("typeId").asInt(), "blah"));
					bDao.updateAccount(account);
					res.setStatus(200);
					res.getWriter().write(om.writeValueAsString(bDao.getAccountById(account.getAccountId())));
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
