package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.User;
import util.ConnectionFactory;

public class UsersByAccount {
	public static void getUsersByAccount(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			String[] params = req.getRequestURI().split("/");
			List<User> users = bDao.getUsersByAccount(Integer.parseInt(params[3]));
			res.getWriter().write(om.writeValueAsString(users));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
