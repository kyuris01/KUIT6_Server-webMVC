package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/user/login")
//public class LoginController extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
//        if (user == null || !user.matchPassword(req.getParameter("password"))) {
//            resp.sendRedirect("/user/loginFailed.jsp");
//            return;
//        }
//        HttpSession session = req.getSession();
//        session.setAttribute("user", user);
//        resp.sendRedirect("/");
//    }
//}
public class LoginController implements Controller {

    public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        if (user == null || !user.matchPassword(req.getParameter("password"))) {
            return "redirect:/user/loginFailed.jsp";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }
}