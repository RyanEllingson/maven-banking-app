package controllers;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BankDAOImpl;
import models.AccountStatus;
import util.ConnectionFactory;

public class Statuses {
	public static void getStatuses(HttpServletRequest req, HttpServletResponse res) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			BankDAOImpl bDao = new BankDAOImpl(conn);
			ObjectMapper om = new ObjectMapper();
			List<AccountStatus> statuses = bDao.getAllStatuses();
			res.getWriter().write(om.writeValueAsString(statuses));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
