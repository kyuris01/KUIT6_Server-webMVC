package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignupController implements Controller {

    private final UserDao userDao;

    public SignupController() {
        this.userDao = new UserDao();
    }

    public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
        userDao.insert(user);
        return "redirect:/user/list";
    }
}
