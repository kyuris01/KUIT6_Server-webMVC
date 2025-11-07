package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) throws Exception {
        userDao.insert(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String userId, @RequestParam String password) throws Exception {
        User loginUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) throws Exception {
        userDao.update(user);
        return "redirect:/user/list";
    }

    @PostMapping("/updateForm")
    public String updateForm(HttpSession session, @RequestParam String userId) throws Exception {
        User user = userDao.findByUserId(userId);

        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {            // 수정되는 user와 수정하는 user가 동일한 경우
                return "user/updateForm";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/form")
    public String form() throws Exception {
        return "user/form";
    }

    @GetMapping("/loginForm")
    public String loginForm() throws Exception {
        return "user/loginForm";
    }

    @GetMapping("/loginFailed")
    public String loginFailed() throws Exception {
        return "user/loginFailed";
    }
}
