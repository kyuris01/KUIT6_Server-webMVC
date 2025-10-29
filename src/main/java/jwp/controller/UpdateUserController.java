package jwp.controller;
import jwp.dao.UserDao;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {

    private final UserDao userDao;

    public UpdateUserController() {
        this.userDao = new UserDao();
    }

    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
        userDao.update(user);
        System.out.println("user 회원정보 수정 완료");
        return "redirect:/user/list";
    }
}