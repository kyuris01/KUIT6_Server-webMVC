package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/user/update")
//public class UpdateUserController extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
//        MemoryUserRepository.getInstance().changeUserInfo(user);
//        System.out.println("user 회원정보 수정 완료");
//        resp.sendRedirect("/user/list");
//    }
//}
public class UpdateUserController implements Controller {
    public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
        MemoryUserRepository.getInstance().changeUserInfo(user);
        System.out.println("user 회원정보 수정 완료");
        return "redirect:/user/list";
    }
}