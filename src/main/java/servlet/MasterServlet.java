package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DeleteRequestHelper;
import service.GetRequestHelper;
import service.PostRequestHelper;
import service.PutRequestHelper;

@WebServlet(
		urlPatterns = {
			"/accountlinks",
			"/accounts",
			"/accounts/*",
			"/currentuser",
			"/home",
			"/login",
			"/logout",
			"/passTime",
			"/register",
			"/roles",
			"/statuses",
			"/types",
			"/users",
			"/users/*"
		}
	)

public class MasterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		GetRequestHelper.process(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PostRequestHelper.process(req, res);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PutRequestHelper.process(req, res);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DeleteRequestHelper.process(req, res);
	}
}
