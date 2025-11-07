package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QuestionDao questionDao;

    @PostMapping("/update")
    public String update(HttpSession session, @RequestParam String questionId, @RequestParam String title, @RequestParam String contents) throws Exception {
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/users/loginForm";
        }

        User user = UserSessionUtils.getUserFromSession(session);
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException("로그인된 유저와 질문 작성자가 다르면 질문을 수정할 수 없습니다.");
        }
        question.updateTitleAndContents(title, contents);
        questionDao.update(question);
        return "redirect:/";
    }

    @PostMapping("/updateForm")
    public String updateForm(HttpSession session, @RequestParam String questionId, Model model) throws Exception {
        if (!UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return "redirect:/user/loginForm";
        }
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        User user = UserSessionUtils.getUserFromSession(session);
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException();
        }
        model.addAttribute("question", question);
        return "/qna/updateForm.jsp";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Question question) throws Exception {
        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());
        return "redirect:/";
    }

    @GetMapping("/form")
    public String createForm(HttpSession session) throws Exception {
        if (UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return "/qna/form.jsp";
        }
        return "redirect:/user/loginForm";
    }

    @GetMapping("/show")
    public String show(@RequestParam String questionId, Model model) throws Exception {
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        model.addAttribute("question", question);
        return "/qna/show.jsp";
    }
}
