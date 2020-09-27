package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.Role;
import util.ConnectionFactory;

public class Roles {
	public static void getRoles(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			List<Role> roles = bDao.getAllRoles();
			res.getWriter().write(om.writeValueAsString(roles));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
