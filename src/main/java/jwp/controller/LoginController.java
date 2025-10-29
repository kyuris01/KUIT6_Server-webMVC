package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController implements Controller {

    private final UserDao userDao;

    public LoginController() {
        this.userDao = new UserDao();
    }

    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = userDao.findByUserId(req.getParameter("userId"));
        if (user == null || !user.matchPassword(req.getParameter("password"))) {
            return "redirect:/user/loginFailed.jsp";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }
}