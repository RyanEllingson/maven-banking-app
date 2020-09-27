package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Message;

public class CurrentUser {
	public static void getCurrentUser(HttpServletRequest req, HttpServletResponse res) {
		try {
			ObjectMapper om = new ObjectMapper();
			HttpSession session = req.getSession(false);
			if (session != null) {
				Object user = session.getAttribute("user");
				res.getWriter().write(om.writeValueAsString(user));
			} else {
				res.getWriter().write(om.writeValueAsString(new Message("not logged in")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
