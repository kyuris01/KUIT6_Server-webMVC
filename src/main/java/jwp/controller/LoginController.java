package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    private final UserDao userDao;

    public LoginController() {
        this.userDao = new UserDao();
    }

    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println(req.getParameter("userId"));
        User user = userDao.findByUserId(req.getParameter("userId"));
        System.out.println(user);
        if (user == null || !user.matchPassword(req.getParameter("password"))) {
            return "redirect:/user/loginFailed.jsp";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }
}