package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.AccountType;
import util.ConnectionFactory;

public class Types {
	public static void getTypes(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			List<AccountType> types = bDao.getAllTypes();
			res.getWriter().write(om.writeValueAsString(types));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
