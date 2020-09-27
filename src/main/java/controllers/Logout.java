package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Message;

public class Logout {
	public static void logout(HttpServletRequest req, HttpServletResponse res) {
		try {
			ObjectMapper om = new ObjectMapper();
			HttpSession session = req.getSession(false);
			if (session == null) {
				res.setStatus(400);
				res.getWriter().write(om.writeValueAsString(new Message("There was no user logged into the session")));
			} else {
				session.invalidate();
				res.setStatus(200);
				res.getWriter().write(om.writeValueAsString(new Message("You have successfully logged out")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
