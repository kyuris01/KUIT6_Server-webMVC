package jwp.controller;
import jwp.dao.UserDao;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class ListUserController implements Controller {

    private final UserDao userDao;

    public ListUserController() {
        this.userDao = new UserDao();
    }

    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            return "redirect:/";
        }

        Collection<User> users = userDao.findAll();
        req.setAttribute("users", users);
        return "/user/list.jsp";
    }
}