package com.itranswarp.learnjava.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.itranswarp.learnjava.bean.School;
import com.itranswarp.learnjava.bean.User;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		School school = new School("No.1 Middle School", "101 North Street");
		User user = new User(123, "Bob", school);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
	}
}
