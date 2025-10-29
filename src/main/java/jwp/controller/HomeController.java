package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/")
public class HomeController implements Controller {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
//		rd.forward(req, resp);
//	}
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		QuestionDao questionDao = new QuestionDao();
		try {
			List<Question> questionList = questionDao.findAll();
			req.setAttribute("questions", questionList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return "/home.jsp";
	}
}
